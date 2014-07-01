package com.yatrix.activity.batch.loader.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.repository.ZipCodeRepository;

@Service
public class AmpActivePartitioner implements Partitioner {
	
	@Autowired
	private ZipCodeRepository zipRepository;

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		
		System.out.println("Grid Size: " + gridSize);
		
		Map<String, ExecutionContext> partitionMap = new LinkedHashMap<String, ExecutionContext>();

		ExecutionContext context;
		
		Long zipCodeCount = zipRepository.count();
		Long partitionSize = zipCodeCount/gridSize;

		for(Integer i = 0; i < gridSize; i++){
			context = new ExecutionContext();
			context.put("AbstractPagingItemReader.read.count.max", ((i + 1) * partitionSize));
			context.put("AbstractPagingItemReader.read.count", (i * partitionSize) );
			
			partitionMap.put("Thread" + i, context);
			
		}
		return partitionMap;
	}

}
