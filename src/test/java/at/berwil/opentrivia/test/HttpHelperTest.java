package at.berwil.opentrivia.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import at.berwil.opentrivia.client.HttpHelperSynchroneous;

public class HttpHelperTest {

	@Test
	public void test() {
		HttpHelperSynchroneous classUnderTest = new HttpHelperSynchroneous();
		
		String url = "https://raw.github.com/square/okhttp/master/README.md";
		String res;
		try {
			res = classUnderTest.retrieveURL(url );
			assertNotNull(res);
		} catch (IOException e) {
			fail("Got Exception " + e.getMessage());
		} 		
	}

}
