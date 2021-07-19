package com.reba.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reba.entities.Property;

@Repository("propCriteriaRepo")
@Transactional
public interface PropertyCriteriaRepository extends CrudRepository<Property,Integer>,JpaSpecificationExecutor<Property>{

}
