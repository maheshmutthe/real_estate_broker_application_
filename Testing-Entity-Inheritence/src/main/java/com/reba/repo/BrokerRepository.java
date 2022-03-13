package com.reba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reba.entities.Broker;

@Repository("brokerRepo")
public interface BrokerRepository extends JpaRepository<Broker,Integer>{

}
