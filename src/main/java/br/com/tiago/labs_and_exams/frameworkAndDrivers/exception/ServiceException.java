package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;


public class ServiceException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	private String message;
	private Exception error;
	
	public ServiceException(String message, Exception erro) {
		this.setMessage(message);
		this.setError(erro);
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