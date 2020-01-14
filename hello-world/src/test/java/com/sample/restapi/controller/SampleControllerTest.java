package com.sample.restapi.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.MessageFormat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.restapi.configuration.ManageDatabaseTest;
import com.sample.restapi.properties.ResponseProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

	@Autowired
	private ResponseProperties responseProperties;

	@Autowired
	private ManageDatabaseTest manageDatabaseTest;

	@Autowired
	private MockMvc mockMvc;

	private static final String helloWorldUrl = "/helloWorld";

	@After
	public void clearUp() {
		manageDatabaseTest.deleteSamples();
	}

	/*
	 * Test URL '/helloWorld'
	 */
	@Test
	public void givenNoUser_whenHelloWorld_thenAssertResponseMessage() throws Exception {
		this.mockMvc.perform(get(helloWorldUrl)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(responseProperties.helloWorld)));
	}

	@Test
	public void givenEmptyUser_whenHelloWorld_thenAssertResponseMessage() throws Exception {
		this.mockMvc.perform(get(helloWorldUrl).param("user", "")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(responseProperties.helloWorld)));
	}

	@Test
	public void givenUserVisited0Times_whenHelloWorld_thenAssertResponseMessage() throws Exception {
		String user = "Test";
		this.mockMvc.perform(get(helloWorldUrl).param("user", user)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(MessageFormat.format(responseProperties.welcomeUser, user))));
	}

	@Test
	public void givenUserVisited1Times_whenHelloWorld_thenAssertResponseMessage() throws Exception {
		String user = "Test";
		manageDatabaseTest.createUser(user, 1);
		this.mockMvc.perform(get(helloWorldUrl).param("user", user)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(MessageFormat.format(responseProperties.welcomeBackUser, user))));
	}

	@Test
	public void givenUserVisited2Times_whenHelloWorld_thenAssertResponseMessage() throws Exception {
		String user = "Test";
		manageDatabaseTest.createUser(user, 2);
		this.mockMvc.perform(get(helloWorldUrl).param("user", user)).andDo(print()).andExpect(status().isOk()).andExpect(
				content().string(containsString(MessageFormat.format(responseProperties.welcomeBackPremiumUser, user))));
	}

	@Test
	public void givenUserVisited10Times_whenHelloWorld_thenAssertResponseMessage() throws Exception {
		String user = "Test";
		manageDatabaseTest.createUser(user, 10);
		this.mockMvc.perform(get(helloWorldUrl).param("user", user)).andDo(print()).andExpect(status().isOk()).andExpect(
				content().string(containsString(MessageFormat.format(responseProperties.welcomeBackPremiumUser, user))));
	}

}
