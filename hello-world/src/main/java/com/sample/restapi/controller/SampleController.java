package com.sample.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.restapi.service.SampleService;

import io.swagger.annotations.ApiOperation;

@RestController
public class SampleController {

	@Autowired
	private SampleService sampleService;

	@ApiOperation(value = "Get the Hello World message.", response = ResponseEntity.class)
	@GetMapping(value = "/helloWorld")
	public ResponseEntity<String> helloWorld(@RequestParam(value = "user", required = false) String user) {
		return sampleService.getResponseByUser(user);
	}

}
