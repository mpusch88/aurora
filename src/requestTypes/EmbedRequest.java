package requestTypes;

public class EmbedRequest extends RequestObject {
	private String image;
	private Float lat, lng;
	
	public EmbedRequest(String image, Float lat, Float lng) {
		super("embed");
		this.image = image;
		this.lat = lat;
		this.lng = lng;
		
		String URI = "http://api.auroras.live/v1/?type=embed";
		if(image != null){
			URI += "&image=" + image;
		}
		if(lat != null || lng != null){
			URI += "&lat=" + lat.toString() + "&long=" + lng.toString();
		}
		this.setUri(URI);
	}
}
