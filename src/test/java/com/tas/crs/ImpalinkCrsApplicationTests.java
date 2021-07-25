package com.tas.crs;

import com.tas.crs.controller.CustomerController;
import com.tas.crs.entity.Customer;
import com.tas.crs.entity.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImpalinkCrsApplicationTests {

	@Autowired
	private CustomerController underTest;
/*
	@Test
	void doesCustomerExist() {
		//given
		Customer customer = new Customer(
				"William",
				"Noutsa",
				"email@email.com",
				Gender.MALE,
				"Douala",
				"Bonaberi"
		);
		//when
		//then
	}*/

}
