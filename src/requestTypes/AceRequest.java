package requestTypes;

import java.util.List;

public class AceRequest extends RequestObject{
	private List<String> data;
	private Float lat, lng;
	
	public AceRequest(List<String> data, Float lat, Float lng) {
		super("ace");
		this.data = data;
		this.lat = lat;
		this.lng = lng;
		
		String URI = "http://api.auroras.live/v1/?type=ace";
		for(String s: data){
			URI += "&data=" + s;
		}
		if(lat != null || lng != null){
			URI += "&lat=" + lat.toString() + "&long=" + lng.toString();
		}
		this.setUri(URI);
	}
}
