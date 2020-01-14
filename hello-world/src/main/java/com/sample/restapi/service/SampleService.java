package com.sample.restapi.service;

import org.springframework.http.ResponseEntity;

public interface SampleService {

	/**
	 * Get responseEntity based on user parameter.
	 * 
	 * @param user
	 * @return ResponseEntity depending on user parameter.
	 */
	public ResponseEntity<String> getResponseByUser(String user);

}
