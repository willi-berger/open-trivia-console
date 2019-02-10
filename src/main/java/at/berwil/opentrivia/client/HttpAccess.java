package at.berwil.opentrivia.client;

import java.io.IOException;

public interface HttpAccess {
	public String retrieveURL(String url) throws IOException;

}
