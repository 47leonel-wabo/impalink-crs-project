package com.tas.crs.repository;

import com.tas.crs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByClosedTrue();

    List<Account> findByClosedFalse();

    List<Account> findByArchivedTrue();

}
