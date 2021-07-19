package com.reba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reba.entities.Deal;

@Repository("dealRepo")
public interface DealRepository extends JpaRepository<Deal,Integer>{

}
