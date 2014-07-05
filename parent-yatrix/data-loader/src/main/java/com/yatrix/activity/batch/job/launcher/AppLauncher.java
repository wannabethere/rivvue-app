package com.yatrix.activity.batch.job.launcher;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yatrix.activity.batch.loader.config.DataLoaderJobConfiguration;

public class AppLauncher {

	public static void main(String[] args){
		AnnotationConfigApplicationContext ctx = 
				   new AnnotationConfigApplicationContext();
		
		ctx.register(DataLoaderJobConfiguration.class);
		
		ctx.refresh();
	}
}


