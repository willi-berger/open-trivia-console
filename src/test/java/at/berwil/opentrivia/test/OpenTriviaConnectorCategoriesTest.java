package at.berwil.opentrivia.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import at.berwil.opentrivia.client.OpenTriviaConnector;
import at.berwil.quiz.QuizException;
import at.berwil.quiz.model.Category;

public class OpenTriviaConnectorCategoriesTest {
	
	@Test
	public void testCategories() {
		OpenTriviaConnector classUnderTest = new OpenTriviaConnector();
		
		List<Category> categories;
		try {
			categories = classUnderTest.getCategories();
			assertNotNull(categories);
			assertTrue("got some categories", categories.size() > 0);

		} catch (QuizException e) {
			fail("Eception: " + e.getMessage());
		} 
	}
	
}
