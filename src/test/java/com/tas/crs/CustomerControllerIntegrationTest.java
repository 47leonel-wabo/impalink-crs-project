package com.tas.crs;


import com.tas.crs.entity.Customer;
import com.tas.crs.entity.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getAllCustomers() {
        ResponseEntity<List> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/customers", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void checkIfCustomerExists() {
        //given
        Customer customer = new Customer(
                "William",
                "Noutsa",
                "email@email.com",
                "680-35-94-97",
                Gender.MALE,
                "Bonaberi",
                "Douala"
        );
        //ResponseEntity<Customer> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/customers/1", Customer.class);
        //when

    }

    /*
    @Test
    public void getCustomer() {
        ResponseEntity<Customer> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/customers/1", Customer.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

     */
}
