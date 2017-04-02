package requestTypes;

public class ImagesRequest extends RequestObject {
	private String action, image;
	
	public ImagesRequest(String action, String image) {
		super("images");
		this.action = action;
		this.image = image;
		
		String URI = "http://api.auroras.live/v1/?type=images";
		
		if(action != null){
			URI += "&action=" + action;
		}else if(image != null){
			URI += "&image=" + image;
		}
		
		this.setUri(URI);
	}

	public String getAction() {
		return action;
	}

	public String getImage() {
		return image;
	}
}
