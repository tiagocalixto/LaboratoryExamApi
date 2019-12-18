package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;


public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private Exception error;
	
	public DataBaseException(String message, Exception error) {
		this.setMessage(message);
		this.setError(error);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getError() {
		return error;
	}

	public void setError(Exception error) {
		this.error = error;
	}
	

}