package com.RESTSample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/cacheControl/")
public class Security {
	private CacheRetriever cacheRetriever;
	
	public Security(){
		cacheRetriever = new CacheRetriever();
	}

	@Path("updateCache/")
	@GET
	@Produces("application/json")
	public Response authenticate(
			@QueryParam("type") String type,
			@QueryParam("cachePeriod") int cachePeriod,
			@QueryParam("authKey") String key){
		if(key != null && key.equals("tempkey")){
			cacheRetriever.setCachePeriod(type, cachePeriod, key);
		
			JSONObject jsonObject = new JSONObject();
            jsonObject.put("Message", "Cache updated.");
            
            return Response.status(200).entity(jsonObject.toString()).build();
		}else{
			JSONObject jsonObject = new JSONObject();
            jsonObject.put("Message", "Invalid key. Update not authorized.");
            
            return Response.status(401).entity(jsonObject.toString()).build();
		}
	}
}
