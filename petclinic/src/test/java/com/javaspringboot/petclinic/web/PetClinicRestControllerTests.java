package com.javaspringboot.petclinic.web;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.javaspringboot.petclinic.model.Owner;

public class PetClinicRestControllerTests { 
	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void testCreateOwner() {
		Owner owner=new Owner();
		owner.setFirstName("Ayse");
		owner.setLastName("Karadeniz");
		
		URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);
		Owner owner2 = restTemplate.getForObject(location, Owner.class);

		MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
		MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));
		
	
		}
	
	@Test
	public void testUpdateOwner() {
		RestTemplate restTemplate = new RestTemplate();
		Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);

		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Asya"));

		owner.setFirstName("Asya");
		restTemplate.put("http://localhost:8080/rest/owner/4", owner);
		owner = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);

		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Asya"));
	}
	
	@Test
	public void testDeleteOwner() {
	restTemplate.delete("http://localhost:8080/rest/owner/1553457570086");

		try {
			restTemplate.getForEntity("http://localhost:8080/rest/owner/1553457570086", Owner.class);
			Assert.fail("Should have not returned owner");
		} catch (RestClientException ex) {

		}
	}

	
	
	@Test
	public void testGetOwnerById() {
		 ResponseEntity<Owner> response= restTemplate.getForEntity("http://localhost:8080/rest/owner/1",Owner.class);
		 
		 MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		 MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Zuleyha"));
	}
	
	@Test
	public void testGetOwnersByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Kaya", List.class);
		
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		
		List<Map<String, String>> body = response.getBody();
		List<String> firstNames = body.stream().map(e->e.get("firstName")).collect(Collectors.toList());
		MatcherAssert.assertThat(firstNames,Matchers.containsInAnyOrder("Zuleyha","Mustafa","Havva"));
	}
	
	@Test
	public void testGetOwners() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
		
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		
		List<Map<String, String>> body = response.getBody();
		List<String> firstNames = body.stream().map(e->e.get("firstName")).collect(Collectors.toList());
		MatcherAssert.assertThat(firstNames,Matchers.containsInAnyOrder("Zuleyha","Mustafa","Havva","Asya"));
		
	}
	

}














