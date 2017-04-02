package com.RESTSample;

import java.util.HashMap;

import requestTypes.RequestObject;

public class DatabaseRelay {
	private HashMap<String, RequestObject> cache;
	
	public DatabaseRelay(){
		cache = new HashMap<>();
		System.out.println("A new cache has been created");
	}
	
	public RequestObject getObject(RequestObject request){
		System.out.println("Getting object from cache with key: " + request.getUri());
		return cache.get(request.getUri());
	}
	
	public void cacheObject(RequestObject request){
		System.out.println("Puting object in cache with key: " + request.getUri());
		cache.put(request.getUri(), request);
	}

	public void setCachePeriod(String type, int timeout, String key) {
		// TODO Auto-generated method stub
		
	}
}
