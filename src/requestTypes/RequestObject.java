package requestTypes;

import javax.ws.rs.core.Response;

public abstract class RequestObject {
	private String uri;
	private String type;
	private Response response;
	
	public RequestObject(String type) {
		this.type = type;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	public int hashCode(){
		return uri.hashCode();
	}
}
