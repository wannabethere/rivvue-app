package com.yatrix.activity.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.yatrix.activity.batch.scheduler.ProcessEventScheduler;
import com.yatrix.activity.process.batch.AmpActiveProcessor;
import com.yatrix.activity.process.batch.AmpActiveReader;
import com.yatrix.activity.process.batch.AmpActiveWriter;
import com.yatrix.activity.process.batch.FacebookSyncupProcessor;
import com.yatrix.activity.process.batch.UserActivityProcessor;
import com.yatrix.activity.batch.listener.LogProcessListener;
import com.yatrix.activity.batch.listener.ProtocolListener;
import com.yatrix.activity.ext.domain.facebook.FacebookSyncupSocialResult;
import com.yatrix.activity.store.mongo.domain.ActivityAndUserToEvents;
import com.yatrix.activity.store.mongo.domain.AmpActiveEventReviews;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.ZipCodes;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

/**
 * @author Kishore Manthangod
 */
@Configuration
@EnableScheduling
@EnableBatchProcessing
public class EventPartitioningJobConfiguration {
	
	@Autowired
	private ResourcePatternResolver resourcePatternResolver;
	
	@Autowired
	private JobBuilderFactory jobBuilders;
	
	@Autowired
	private StepBuilderFactory stepBuilders;
	
	@Autowired
	private IUserActivityCatalogService userActivityCatalogService;
	
	@Autowired
	@Qualifier("userActivityPartitioner")
	private Partitioner pendingUserPartitioner;
	
	@Autowired
	@Qualifier("facebookSyncupPartitioner")
	private Partitioner facebookSyncupPartitioner;
	
	@Autowired
	@Qualifier(value="userActivityReader")
	private ItemReader<UserActivity> eventProcessingReader;
	
	@Autowired
	@Qualifier("userActivityWriter")
	private ItemWriter<ActivityAndUserToEvents> eventProcessingWriter;
	
	@Autowired
	@Qualifier(value="facebookSyncupReader")
	private ItemReader<UserEvent> facebookSyncupReader;
	
	@Autowired
	@Qualifier("facebookSyncupWriter")
	private ItemWriter<FacebookSyncupSocialResult> facebookSyncupWriter;
	
	@Autowired
	private AmpActivePartitioner ampActivePartitioner;
	
	@Autowired
	private AmpActiveReader ampActiveReader;
	
	@Autowired
	private AmpActiveWriter ampActiveWriter;

		
	@Bean(name="pendingEventPartitioningJob")
	public Job pendingEventPartitioningJob(){
		return jobBuilders.get("pendingEventPartitioningJob")
				.listener(protocolListener())
				.start(eventProcessingPartitionStep())
				.build();
	}
	
	@Bean(name="facebookSyncupPartitioningJob")
	public Job facebookSyncupPartitioningJob(){
		return jobBuilders.get("facebookSyncupPartitioningJob")
				.listener(protocolListener())
				.start(facebookSynupPartitionStep())
				.build();
	}
	
	@Bean
	public Step eventProcessingPartitionStep(){
		return stepBuilders.get("partitionStep")
				.partitioner(pendingEventStep()).gridSize(10)
				.partitioner("flatfileToDbStep", pendingUserPartitioner)
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	public Step facebookSynupPartitionStep(){
		return stepBuilders.get("facebookSyncupStep")
				.partitioner(facebookSyncupStep()).gridSize(10)
				.partitioner("facebookSyncupStep", facebookSyncupPartitioner)
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	public Step pendingEventStep(){
		return stepBuilders.get("pendingEventStep")
				.<UserActivity, ActivityAndUserToEvents>chunk(1)
				.reader(eventProcessingReader)
				.processor(eventProcessingProcessor())
				.writer(eventProcessingWriter)
				.listener(logProcessListener())
				.build();
	}
	
	@Bean
	public Step facebookSyncupStep(){
		return stepBuilders.get("facebookSyncupStep")
				.<UserEvent, FacebookSyncupSocialResult>chunk(1)
				.reader(facebookSyncupReader)
				.processor(facebookSyncupProcessor())
				.writer(facebookSyncupWriter)
				.listener(logProcessListener())
				.build();
	}
	
	@Bean(name="ampDataPartitionJob")
	public Job ampDataPartitionJob(){
		return jobBuilders.get("ampDataPartitionJob")
				.listener(protocolListener())
				.start(ampDataPartitionStep())
				.build();
	}
		
	@Bean
	public Step ampDataPartitionStep(){
		return stepBuilders.get("ampPartitionStep")
				.<ZipCodes, AmpActiveEventReviews>chunk(1)
				.reader(ampActiveReader)
				.processor(ampDataProcessor())
				.writer(ampActiveWriter)
				.listener(logProcessListener())
				.build();
	}
	
	@Bean
	public Step ampPartitionStep(){
		return stepBuilders.get("ampDataPartitionStep")
				.partitioner(ampDataPartitionStep()).gridSize(10)
				.partitioner("ampDataPartitionStep", ampActivePartitioner)
				.taskExecutor(taskExecutor())
				.build();
	}
	
		
	@Bean
	public ItemProcessor<ZipCodes, AmpActiveEventReviews> ampDataProcessor(){
		return new AmpActiveProcessor();
	}
	

		
	@Bean
	public ItemProcessor<UserActivity, ActivityAndUserToEvents> eventProcessingProcessor(){
		return new UserActivityProcessor();
	}
	
	@Bean
	public ItemProcessor<UserEvent, FacebookSyncupSocialResult> facebookSyncupProcessor(){
		return new FacebookSyncupProcessor();
	}
	
	
	@Bean
	public ProtocolListener protocolListener(){
		return new ProtocolListener();
	}
	
	@Bean
	public LogProcessListener logProcessListener(){
		return new LogProcessListener();
	}
	
	@Bean
	public ProcessEventScheduler processEventScheduler(){
		return new ProcessEventScheduler();
	}
	
	@Bean
	public DataSource dataSource(){
		System.out.println("Creating datasource");
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-hsqldb.sql")
				.addScript("classpath:org/springframework/batch/core/schema-hsqldb.sql")
				.setType(EmbeddedDatabaseType.HSQL)
				.build();
		
	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(4);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

}
