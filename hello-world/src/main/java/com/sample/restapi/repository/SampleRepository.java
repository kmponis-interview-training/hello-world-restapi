package com.sample.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.restapi.model.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

	Sample findByUser(String user);

}
