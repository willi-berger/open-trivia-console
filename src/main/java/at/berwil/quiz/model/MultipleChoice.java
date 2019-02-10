package at.berwil.quiz.model;

import java.util.Arrays;

public class MultipleChoice {

	String question;
	String answer;
	String[] wrongAnswers;
	final int nAnswers;
	int nWrongAnswers;
	
	public MultipleChoice(String question, int nAnswers) {
		super();
		this.question = question;
		this.nAnswers = nAnswers;
		this.wrongAnswers = new String[nAnswers-1];
		this.nWrongAnswers = 0;
	}	
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String[] getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(String[] wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public int getnAnswers() {
		return nAnswers;
	}

	public void addWrongAnswer(String answer) {
		if (nWrongAnswers >= wrongAnswers.length) {
			throw new RuntimeException("Too many answers added");
		}
		wrongAnswers[nWrongAnswers++] = answer;
	}
		
	@Override
	public String toString() {
		return "MultipleChoice [question=" + question + ", answers=" + Arrays.toString(wrongAnswers) + ", nAnswers="
				+ nAnswers + ", answer=" + answer + "]";
	}

}
