package com.yatrix.activity.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yatrix.activity.store.config.MongoConfig;
import com.yatrix.activity.store.mongo.domain.ZipCodes;
import com.yatrix.activity.store.mongo.repository.ZipCodeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoConfig.class, TestSocialConfig.class})
public class ZipCodesTest {
	
	@Autowired
	private ZipCodeRepository zipRepository;
	
	@Test
	public void testMethod(){
		System.out.println("Page Count: " + zipRepository.count());
		Page<ZipCodes> zipCodes = zipRepository.findAll(new PageRequest(1, 20));
		
		for(ZipCodes zipCode : zipCodes){
			System.out.println("Zip code: " + zipCode);
		}
	}

}
