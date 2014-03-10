package com.yatrix.activity.batch.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Service;

@Service("facebookSyncupPartitioner")
public class FacebookSyncupPartitioner implements Partitioner {

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		
		System.out.println("Grid Size: " + gridSize);
		
		Map<String, ExecutionContext> partitionMap = new LinkedHashMap<String, ExecutionContext>();

		ExecutionContext context;

		for(Integer i = 0; i < 5; i++){
			context = new ExecutionContext();
			context.put("AbstractPagingItemReader.read.count.max", ((i + 1) *100));
			context.put("AbstractPagingItemReader.read.count", (i*100) );
			
			partitionMap.put("Thread" + i, context);
			
		}
		return partitionMap;
	}

}
