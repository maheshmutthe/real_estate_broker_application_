package com.reba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reba.entities.User;

@Repository("userRepo")
public interface UserRepository extends JpaRepository<User,Integer>{

	@Query("SELECT u FROM user u WHERE u.userName = ?1")
	public User findByUserName(String userName);
}
