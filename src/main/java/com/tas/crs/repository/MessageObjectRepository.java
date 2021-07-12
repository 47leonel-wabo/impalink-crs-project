package com.tas.crs.repository;

import com.tas.crs.entity.MessageObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageObjectRepository extends JpaRepository<MessageObject, Long> {
}
