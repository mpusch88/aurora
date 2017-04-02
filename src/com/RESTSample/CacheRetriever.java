package com.RESTSample;

import javax.ws.rs.core.Response;

import requestTypes.RequestObject;

public class CacheRetriever {
	private DatabaseRelay database;
	private RequestCreator requestCreator;
	
	public CacheRetriever(){
		database = new DatabaseRelay();
		requestCreator = new RequestCreator();
	}

	public Response retrieve(RequestObject request, String noCaching) {
		
		// Check if object is already in database
		RequestObject cachedItem = database.getObject(request);
		
		// if the object was found AND the request is not no-caching return object 
		if(cachedItem != null && !noCaching.equals("true")){
			return cachedItem.getResponse();
		// otherwise
		}else{
			// get response from aurora
			cachedItem = requestCreator.getResponse(request);
			if(cachedItem == null){
				return null;
			}
			// put it in the database
			database.cacheObject(cachedItem);
			return cachedItem.getResponse();
		}
	}
	
	public void setCachePeriod(String type, int timeout, String key){
		database.setCachePeriod(type, timeout, key);
	}
}
