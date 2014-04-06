package com.yatrix.activity.service.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.hystrix.fb.command.HystrixSocialResult;


public class TestFuture {

	
	public static void main(String[] args){
		TestFacebookCommand command = new TestFacebookCommand();
		command.queue();
		System.out.println("Running as usual");
		TestFacebookCommand command1 = new TestFacebookCommand();
		command1.queue();
		System.out.println("Running as usual2");
		TestFacebookCommand command2 = new TestFacebookCommand();
		command2.queue();
		
		System.out.println("Running as usual3");
		System.out.println("Running as usual4");
		System.out.println("Running as usual5");
		System.out.println("Running as usual6");
		TestFacebookCommand command3 = new TestFacebookCommand();
		command3.queue();
	}
	
	public static class TestFacebookCommand extends HystrixCommand<HystrixSocialResult>{

		protected TestFacebookCommand() {
			 super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
				      .andCommandKey(HystrixCommandKey.Factory.asKey("FacebookCommand"))
				      .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
				      .andCommandPropertiesDefaults(
				      // we default to a one minute timeout for primary
				        HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		}

		@Override
		protected HystrixSocialResult run() throws Exception {
			System.out.println("I am starting");
			Thread.sleep(20000);
			System.out.println("I am done");
			return new HystrixSocialResult(true, "122312321", "got it");
		}
		
		
	}
	
	
	
	
}
