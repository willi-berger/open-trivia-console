package at.berwil.quiz;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.berwil.opentrivia.client.OpenTriviaConnector;
import at.berwil.quiz.model.Category;
import at.berwil.quiz.model.Level;
import at.berwil.quiz.model.MultipleChoice;

public class Quiz {
	
	private Scanner sc;

	public Quiz(QuizProvider quizProvider) {
		super();
		this.quizProvider = quizProvider;
	}

	private static final Logger LOG = LoggerFactory.getLogger(Quiz.class);
	
	private QuizProvider quizProvider;
	
	private void quizGameConsole() throws QuizException {
		this.sc = new Scanner(System.in);

		try {
			System.out.println("Hello OpenTrivia");
			System.out.println("----------------");

			int selectedCategory = selectCategory();
			System.out.println("Selected category: " + selectedCategory);

			while (true) {
				askMultipleChoice(selectedCategory);
				System.out.print("Another question?  y/n.. ");
				String yesNo = sc.next();
				if(yesNo.toLowerCase().startsWith("n")) {
					break;
				}
				
			}
			System.out.println("Good bye");
			
		}
		finally {
			this.sc.close();
		}
	}

	private void askMultipleChoice(int category) throws QuizException {

		MultipleChoice mc =quizProvider.getMultipleChoiceQuestion(category, Level.easy);
		System.out.println(mc.getQuestion());  //TODO nice to have html decode entities
		// list answers
		int n = mc.getnAnswers();
		int iRight = Double.valueOf( Math.floor(Math.random()*n)).intValue();
		int j = 0;
		for (int i = 0; i < n; i++) {
			if (i==iRight) {
				System.out.println((i+1) + ": " + mc.getAnswer());
			} else {
				System.out.println((i+1) + ": " + mc.getWrongAnswers()[j++]);
			}
		}
		// enter answers
		System.out.print("Answer (1..4): ");
		int answer = sc.nextInt();
		if (answer-1 == iRight) {
			System.out.println("Correct :-)");
		} else {
			System.out.println("Wrong :-( ");
			System.out.println("Right answer is: "+(iRight+1) + " - " + mc.getAnswer());
		}		
	}

	private int selectCategory() throws QuizException {
		List<Category> categories = quizProvider.getCategories();
		System.out.println("Categories");
		for (Category cat : categories) {
			System.out.printf("%d:\t%s\n", cat.getId(), cat.getName());
		} 
		System.out.println("----------------");
		System.out.print("Enter category: ");
		int  i = sc.nextInt();
		return i;	
	}

	public static void main(String[] args) {
		Quiz q = new Quiz(new OpenTriviaConnector());
		try {
			q.quizGameConsole();
		} catch (QuizException e) {
			System.out.println("An error occurred, please try later");
			LOG.error(e.getMessage(), e);
		} 
	}

}
