package at.berwil.opentrivia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.berwil.opentrivia.client.OpenTriviaConnector;
import at.berwil.quiz.QuizException;
import at.berwil.quiz.model.Level;
import at.berwil.quiz.model.MultipleChoice;

public class OpenTriviaConnectorQuestionTest {
	

	@Test
	public void testRetrieveMultipleChoice() {
		OpenTriviaConnector classUnderTest = new OpenTriviaConnector();
		try {
			MultipleChoice mc = classUnderTest.getMultipleChoiceQuestion(9, Level.easy);
			assertNotNull(mc);
			assertEquals(4, mc.getnAnswers());
			assertNotNull(mc.getQuestion());
			assertEquals(3, mc.getWrongAnswers().length);
			
		} catch (QuizException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRetrieveMultipleChoiceUnknownCategory() {
		OpenTriviaConnector classUnderTest = new OpenTriviaConnector();
		try {
			classUnderTest.getMultipleChoiceQuestion(999,Level.medium);
			fail("should fail invalid category");
		} catch (QuizException e) {
			assertNotNull(e);		
		}
	}

}
