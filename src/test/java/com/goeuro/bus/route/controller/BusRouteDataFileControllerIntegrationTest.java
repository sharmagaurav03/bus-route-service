package com.goeuro.bus.route.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BusRouteDataFileControllerIntegrationTest {

	private static final String DATA_ROUTE = "/api/route/data/load";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void loadValidBusRouteDataFile() {
		ResponseEntity<String> responseEntity = restTemplate.exchange(DATA_ROUTE, HttpMethod.PUT,
				new HttpEntity<String>(new File("routes/test-routes").getAbsolutePath(), new HttpHeaders()), String.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void doNotLoadErredBusRouteDataFile() {
		ResponseEntity<String> responseEntity = restTemplate.exchange(DATA_ROUTE, HttpMethod.PUT,
				new HttpEntity<String>(new File("routes/test-routes-alpha-numeric-error").getAbsolutePath(),
						new HttpHeaders()),	String.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void doNotLoadErredRowBusRouteDataFile() {
		ResponseEntity<String> responseEntity = restTemplate.exchange(DATA_ROUTE, HttpMethod.PUT,
				new HttpEntity<String>(new File("routes/test-routes-error-less-than-3-tokens-in-row").getAbsolutePath(),
						new HttpHeaders()),
				String.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void fileNotFoundResponseForNonExistingBusRouteDataFile() {
		ResponseEntity<String> responseEntity = restTemplate.exchange(DATA_ROUTE, HttpMethod.PUT,
				new HttpEntity<String>(new File("routes/not-existing-file").getAbsolutePath(), new HttpHeaders()),
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
}
