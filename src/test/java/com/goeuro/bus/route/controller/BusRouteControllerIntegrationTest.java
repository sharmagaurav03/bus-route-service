package com.goeuro.bus.route.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeuro.bus.route.dto.BusRouteQueryResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BusRouteControllerIntegrationTest {

	private static final String DATA_ROUTE = "/api/route/data/load";

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate.exchange(DATA_ROUTE, HttpMethod.PUT,
				new HttpEntity<String>(new File("routes/test-routes").getAbsolutePath(), new HttpHeaders()), String.class);
	}

	@Test
	public void testDirectRoute() {
		ResponseEntity<BusRouteQueryResponse> response = restTemplate.getForEntity("/api/direct?dep_sid=3&arr_sid=4",
				BusRouteQueryResponse.class);
		BusRouteQueryResponse responseObject = response.getBody();
		assertEquals(true, responseObject.isConnected());
	}

	@Test
	public void testNonDirectRoute() {
		ResponseEntity<BusRouteQueryResponse> response = restTemplate.getForEntity("/api/direct?dep_sid=33&arr_sid=4",
				BusRouteQueryResponse.class);
		BusRouteQueryResponse responseObject = response.getBody();
		assertEquals(false, responseObject.isConnected());
	}

}
