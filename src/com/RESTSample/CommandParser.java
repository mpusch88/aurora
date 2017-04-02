package com.RESTSample;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import com.mashape.unirest.http.exceptions.UnirestException;

import requestTypes.*;

@Path("/")
public class CommandParser {
	private CacheRetriever cacheRetriever = new CacheRetriever();
	
	@Path("{path}")
	@GET
	@Produces({"application/json", "image/jpeg"})
	public Response parse(
			@PathParam("path") String path, 
			@QueryParam("type") String type,
			@QueryParam("data") List<String> data,
			@QueryParam("lat") Float lat,
			@QueryParam("long") Float lng,
			@QueryParam("action") String action,
			@QueryParam("start") String start,
			@QueryParam("end") String end,
			@QueryParam("image") String image,
			@QueryParam("ace") String ace,
			@QueryParam("archive") String archive,
			@QueryParam("forecast") String forecast,
			@QueryParam("images") String images,
			@QueryParam("probability") String probability,
			@QueryParam("threeday") String threeday,
			@QueryParam("twentysevenday") String twentysevenday,
			@QueryParam("weather") String weather,
			@QueryParam("id") String id,
			@QueryParam("no-caching") String noCaching) throws JSONException, UnirestException{
		RequestObject request = null;
		
		switch(type){
		case "locations": request = new LocationsRequest(); break;
		case "ace": request = new AceRequest(data, lat, lng); break;
		case "archive": request = new ArchiveRequest(action, start, end); break;
		case "embed": request = new EmbedRequest(image, lat, lng); break;
		case "images": request = new ImagesRequest(action, image); break;
		case "weather": request = new WeatherRequest(lat, lng, forecast); break;
		case "all": request = new AllRequest(lat, lng, ace, archive, forecast, images, probability, threeday, twentysevenday, weather); break;
		case "map": request = new MapRequest(id); break;
		}
		
		if(request == null){
			return Response.status(400).build();
		}
		return cacheRetriever.retrieve(request, noCaching);
	}
}
