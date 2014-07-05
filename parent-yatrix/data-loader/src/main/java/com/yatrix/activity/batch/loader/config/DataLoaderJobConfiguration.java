package com.yatrix.activity.batch.loader.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yatrix.activity.batch.loader.AmpActiveProcessor;
import com.yatrix.activity.batch.loader.AmpActiveReader;
import com.yatrix.activity.batch.loader.AmpActiveWriter;
import com.yatrix.activity.batch.loader.listener.LogProcessListener;
import com.yatrix.activity.batch.loader.listener.ProtocolListener;
import com.yatrix.activity.batch.loader.scheduler.DataLoadScheduler;
import com.yatrix.activity.store.mongo.domain.ZipCodes;
import com.yatrix.activity.store.mongo.domain.loader.AmpActiveEventReviews;

/**
 * @author Kishore Manthangod
 */
@Configuration
@EnableScheduling
@EnableBatchProcessing
@ComponentScan(basePackages = {"com.yatrix.activity"})
@Import({MongoConfig.class})
public class DataLoaderJobConfiguration {
	
	@Autowired
	private ResourcePatternResolver resourcePatternResolver;
	
	@Autowired
	private JobBuilderFactory jobBuilders;
	
	@Autowired
	private StepBuilderFactory stepBuilders;
	
	@Autowired
	private AmpActivePartitioner ampActivePartitioner;
	
	@Autowired
	private AmpActiveReader ampActiveReader;
	
	@Autowired
	private AmpActiveWriter ampActiveWriter;

		
	
	
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
				.<ZipCodes, List<AmpActiveEventReviews>>chunk(1)
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
	public ItemProcessor<ZipCodes, List<AmpActiveEventReviews>> ampDataProcessor(){
		return new AmpActiveProcessor();
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
	public DataLoadScheduler processEventScheduler(){
		return new DataLoadScheduler();
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
	
	@Bean
	public RestTemplate initializeRestTemplate(){
		return new RestTemplate();
	}

}
