package at.berwil.quiz;

/**
 * Exception class for Quiz Service 
 * @author willi
 */
public class QuizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7961630391793703804L;

	public QuizException() {
		// TODO Auto-generated constructor stub
	}

	public QuizException(String message) {
		super(message);
	}

	public QuizException(Throwable cause) {
		super(cause);
	}

	public QuizException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
