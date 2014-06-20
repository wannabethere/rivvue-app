package com.yatrix.activity.batch.scheduler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

public class ProcessEventScheduler {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("pendingEventPartitioningJob")
	private Job pendingEventPartitioningJob;
	
	@Autowired
	@Qualifier("facebookSyncupPartitioningJob")
	private Job facebookSyncupPartitioningJob;
	
	@Autowired
	@Qualifier("ampDataPartitionJob")
	private Job ampDataPartitionJob;
	
	//@Scheduled(initialDelay=60000, fixedDelay=300000)
	protected void processPendingEvents(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++Running job...");
		Map<String, JobParameter> jobParams = new LinkedHashMap<String, JobParameter>();
		jobParams.put("Id", new JobParameter(System.currentTimeMillis(), true));
		JobParameters params = new JobParameters(jobParams);
		
		try {
			jobLauncher.run(pendingEventPartitioningJob, params);
		} catch (Exception e) {
			System.out.println("-------------------------------------- Job running exception");
			e.printStackTrace();
		} 	
	}
	
	@Scheduled(fixedDelay=300000)
	protected void facebookSyncupEvents(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++Running job...");
		Map<String, JobParameter> jobParams = new LinkedHashMap<String, JobParameter>();
		jobParams.put("Id", new JobParameter(System.currentTimeMillis(), true));
		JobParameters params = new JobParameters(jobParams);
		
		try {
			jobLauncher.run(facebookSyncupPartitioningJob, params);
		} catch (Exception e) {
			System.out.println("-------------------------------------- Job running exception");
			e.printStackTrace();
		} 	
	}
	
	@Scheduled(initialDelay=60000, fixedDelay=300000)
	protected void ampActiveDataProcessing(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++Amp Active Data Running job...");
		Map<String, JobParameter> jobParams = new LinkedHashMap<String, JobParameter>();
		jobParams.put("Id", new JobParameter(System.currentTimeMillis(), true));
		JobParameters params = new JobParameters(jobParams);
		
		try {
			jobLauncher.run(ampDataPartitionJob, params);
		} catch (Exception e) {
			System.out.println("-------------------------------------- Job running exception");
			e.printStackTrace();
		} 	
	}

}
