package com.reba.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reba.entities.Property;

@Repository("propRepo")
public interface PropertyRepository extends JpaRepository<Property,Integer>{

	@Query("SELECT p FROM property p WHERE p.status = true")
	public List<Property> getAllUnsold();
}
