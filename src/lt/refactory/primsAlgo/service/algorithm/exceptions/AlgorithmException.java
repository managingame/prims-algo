package lt.refactory.primsAlgo.service.algorithm.exceptions;

public class AlgorithmException extends Exception {

	private static final long serialVersionUID = 1L;

	public AlgorithmException() {}

	public AlgorithmException(String message) {
		super(message);
	}
	
	public AlgorithmException(String message, Throwable innerException) {
		super(message, innerException);
	}
}
