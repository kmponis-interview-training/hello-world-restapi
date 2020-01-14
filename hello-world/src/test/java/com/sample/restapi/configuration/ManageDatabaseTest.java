package com.sample.restapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.restapi.model.Sample;
import com.sample.restapi.repository.SampleRepository;

@Service
public class ManageDatabaseTest {

	@Autowired
	private SampleRepository sampleRepository;

	/**
	 * Create Sample objects user
	 */
	public void createUser(String user, int countVisit) {
		sampleRepository.save(Sample.builder().user(user).countVisit(countVisit).build());
	}

	/**
	 * Delete all Sample objects
	 */
	public void deleteSamples() {
		sampleRepository.deleteAll();
	}

}
