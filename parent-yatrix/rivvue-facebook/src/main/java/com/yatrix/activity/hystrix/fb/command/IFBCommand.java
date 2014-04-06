package com.yatrix.activity.hystrix.fb.command;

import java.util.concurrent.Future;

import com.yatrix.activity.store.mongo.domain.UserActivity;

public interface IFBCommand<Request,Response> {
	Future<Response> executeFacebookCommand(Request pUserRequest);	
}
