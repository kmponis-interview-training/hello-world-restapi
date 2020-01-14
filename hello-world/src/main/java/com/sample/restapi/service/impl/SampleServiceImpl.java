package com.sample.restapi.service.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.restapi.model.Sample;
import com.sample.restapi.properties.ResponseProperties;
import com.sample.restapi.repository.SampleRepository;
import com.sample.restapi.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private ResponseProperties responseProperties;

	@Autowired
	private SampleRepository sampleRepository;

	@Override
	public ResponseEntity<String> getResponseByUser(String user) {
		String responseBody;
		if (null == user || user.isEmpty()) {
			responseBody = responseProperties.helloWorld;
		} else {
			Sample sample = sampleRepository.findByUser(user);
			if (null == sample) {
				responseBody = MessageFormat.format(responseProperties.welcomeUser, user);
				this.saveUser(user);
			} else {
				if (sample.getCountVisit() > 1) {
					responseBody = MessageFormat.format(responseProperties.welcomeBackPremiumUser, user);
				} else {
					responseBody = MessageFormat.format(responseProperties.welcomeBackUser, user);
				}
				this.updateUser(sample);
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}

	/**
	 * Adding user to DB and set visit variable to one(1).
	 * 
	 * @param user
	 */
	private void saveUser(String user) {
		sampleRepository.save(Sample.builder().user(user).countVisit(1).build());
	}

	/**
	 * Update user who already exists in DB. Increase his visit variable by one(1).
	 * 
	 * @param sample
	 */
	private void updateUser(Sample sample) {
		sample.setCountVisit(sample.getCountVisit() + 1);
		sampleRepository.save(sample);
	}

}
