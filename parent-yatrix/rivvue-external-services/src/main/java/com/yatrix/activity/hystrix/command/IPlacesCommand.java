package com.yatrix.activity.hystrix.command;

import java.util.concurrent.Future;



public interface IPlacesCommand<V,T> {
	
	Future<T> loadPlaceDetails(String term, String location);
	Future<T> loadPlaceDetails(V Object);

}
