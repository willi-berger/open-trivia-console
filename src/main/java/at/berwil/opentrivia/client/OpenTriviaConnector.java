package at.berwil.opentrivia.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.berwil.quiz.QuizException;
import at.berwil.quiz.QuizProvider;
import at.berwil.quiz.model.Category;
import at.berwil.quiz.model.Level;
import at.berwil.quiz.model.MultipleChoice;

/**
 * implements {@link QuizProvider} using the OpenTrivia service
 * @author willi
 *
 */
public class OpenTriviaConnector implements QuizProvider {
	
	public OpenTriviaConnector() {
		super();
		this.httpAccess = new HttpHelperSynchroneous();
	}

	public OpenTriviaConnector(HttpAccess httpAccess) {
		super();
		this.httpAccess = httpAccess;
	}

	private static final Logger LOG = LoggerFactory.getLogger(OpenTriviaConnector.class);

	private HttpAccess httpAccess;
	
	private static final String OpenTriviaApiUrl = "https://opentdb.com/api.php";
	
	private static final String OpenTriviaCategoriesApiUrl = "https://opentdb.com/api_category.php";
	
	public List<Category> getCategories() throws QuizException {
		
		LOG.info("getCategories");
		List<Category> categories = new ArrayList<Category>();
		try {
			String categoriesJSON = httpAccess.retrieveURL(OpenTriviaCategoriesApiUrl);
			LOG.debug("Response: " + categoriesJSON);
			//{
			//	"trivia_categories": [{
			//			"id": 9,
			//			"name": "General Knowledge"
			//		}, {
			//			"id": 10,
			//			"name": "Entertainment: Books"
			//		}
			//	]
			//}
			JSONObject categoriesJSONobject = new JSONObject(categoriesJSON);
			JSONArray categoriesJSONArray = categoriesJSONobject.getJSONArray("trivia_categories");
			for (Object o : categoriesJSONArray) {
				//LOG.debug(o.toString());
				JSONObject jsonObj = (JSONObject) o;
				int id = jsonObj.getInt("id");
				String name = jsonObj.getString("name");
				LOG.debug("id=" + id + " name=" + name);
				categories.add(new Category(id, name));
			}
			return categories;
		} catch (IOException e) {
			LOG.error("Unexpected exception "+e.getMessage(), e);
			throw new QuizException(e.getMessage(), e);
		}
	
	}

	@Override
	public MultipleChoice getMultipleChoiceQuestion(int category, Level level) throws QuizException {
		// https://opentdb.com/api.php?amount=1&category=11&difficulty=easy&type=multiple
		//{
		//"response_code": 0,
		//	"results": [{
		//			"category": "Entertainment: Film",
		//			"type": "multiple",
		//			"difficulty": "easy",
		//			"question": "&quot;The first rule is: you don&#039;t talk about it&quot; is a reference to which movie?",
		//			"correct_answer": "Fight Club",
		//			"incorrect_answers": ["The Island", "Unthinkable", "American Pie"]
		//		}
		//	]
		//}
		//                              amount=10&category=11&difficulty=medium&type=multiple
		String url = OpenTriviaApiUrl+"?amount=1&category="+category+"&difficulty="+level.toString()+"&type=multiple";
		try {
			String responseStr = httpAccess.retrieveURL(url);
			LOG.debug("response: " + responseStr);
			JSONObject responseJSON = new JSONObject(responseStr);
			
			int responseCode = responseJSON.getInt("response_code");
			if (responseCode != 0) {
				throw new QuizException("Unexpected resopnse code " + responseCode);
			}
			JSONArray resultsJSONarray = responseJSON.getJSONArray("results");
			JSONObject resultJSON = resultsJSONarray.getJSONObject(0);
			MultipleChoice multipleChoice = new MultipleChoice(
					resultJSON.getString("question"), 4);
			multipleChoice.setAnswer(resultJSON.getString("correct_answer"));
			
			JSONArray incorrectAnswers = resultJSON.getJSONArray("incorrect_answers");
			for (Object o : incorrectAnswers) {
				String incorrectAnswer = (String) o;
				multipleChoice.addWrongAnswer(incorrectAnswer);
			}
			LOG.debug(multipleChoice.toString());
			return multipleChoice;
			
		} catch (IOException e) {
			LOG.error("Unexpected exception "+e.getMessage(), e);
			throw new QuizException(e.getMessage(), e);
		}
		
		
	}
	
}
