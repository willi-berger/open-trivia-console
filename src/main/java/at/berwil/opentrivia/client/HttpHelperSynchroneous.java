package at.berwil.opentrivia.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelperSynchroneous implements HttpAccess {

	private static final Logger LOG = LoggerFactory.getLogger(HttpHelperSynchroneous.class);
	OkHttpClient client = new OkHttpClient();

	@Override
	public String retrieveURL(String url) throws IOException {
		LOG.info("build request and invoke URL: " + url);
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		} catch (IOException e) {
			LOG.error("Could not execute request " + url + " got Exception: " + e.getMessage());
			throw e;
		}
	}
}
