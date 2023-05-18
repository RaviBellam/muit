package com.techwl.stn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techwl.stn.dao.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT c FROM User c where c.active = 1 and c.email = :name and c.password = :pwd")
	User validateUser(@Param("name")String userName, @Param("pwd")String password);
	
	
}
