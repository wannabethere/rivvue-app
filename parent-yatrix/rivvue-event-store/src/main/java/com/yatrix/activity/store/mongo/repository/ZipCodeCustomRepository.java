package com.yatrix.activity.store.mongo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.ZipCodes;

@Service
public class ZipCodeCustomRepository {
	
	class ZipCodeList {
		List<ZipCodes> zipCodes;
	}

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Page<ZipCodes> findAllDistinct(Pageable pageable){
		List<String> zipCodes = mongoTemplate.getCollection("ZipCodes").distinct("primary_city");
		
		for(String zipCode : zipCodes){
			System.out.println(zipCode);
		}
		
		return null;
	}
	
	public Page<ZipCodes> findAllGroupBy(Pageable pageable){
		
		GroupByResults<ZipCodes> zipCodes = mongoTemplate.group("ZipCodes", GroupBy.keyFunction("function(doc){return {primary_city:doc.primary_city}}")
				.initialDocument("{\"id\":\"0\"}").reduceFunction("function(doc, prev) { doc}"), ZipCodes.class);
		
		
		for(ZipCodes zipCode : zipCodes){
			System.out.println(zipCode);
		}
		
		return null;
	}
	
	public long count(){
		return 0l;
	}
}
