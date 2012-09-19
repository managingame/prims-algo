package lt.refactory.primsAlgo.graph.exception;

public class AddEdgeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddEdgeException(String msg) {
		super(msg);
	}

	public AddEdgeException(String msg, Throwable t) {
		super(msg, t);
	}
}
