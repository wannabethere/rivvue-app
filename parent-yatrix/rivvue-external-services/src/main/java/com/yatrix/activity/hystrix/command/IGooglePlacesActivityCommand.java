package com.yatrix.activity.hystrix.command;

import java.util.concurrent.Future;

import com.yatrix.activity.ext.domain.google.PlacesRequest;
import com.yatrix.activity.ext.domain.google.PlacesSearchResults;



public interface IGooglePlacesActivityCommand extends IPlacesCommand<PlacesRequest, PlacesSearchResults> {

}
