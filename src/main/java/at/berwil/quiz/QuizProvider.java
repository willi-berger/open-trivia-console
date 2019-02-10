package at.berwil.quiz;

import java.util.List;

import at.berwil.quiz.model.Category;
import at.berwil.quiz.model.Level;
import at.berwil.quiz.model.MultipleChoice;
/**
 * QuizProvider interface
 * @author willi
 *
 */
public interface QuizProvider {
	
	/**
	 * get a List of selectable Quiz categories
	 * @return
	 * @throws QuizException
	 */
	public List<Category> getCategories() throws QuizException;

	/**
	 * retrieve a multiple choice question for <i>category</i>
	 * @param category
	 * @param level
	 * @throws QuizException 
	 */
	public MultipleChoice getMultipleChoiceQuestion(int category, Level level) throws QuizException;
}
