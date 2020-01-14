package com.sample.restapi.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:response.properties" })
public class ResponseProperties {

	@Value("${hello.world}")
	public String helloWorld;

	@Value("${welcome.user}")
	public String welcomeUser;

	@Value("${welcome.back.user}")
	public String welcomeBackUser;

	@Value("${welcome.back.premium.user}")
	public String welcomeBackPremiumUser;

}
