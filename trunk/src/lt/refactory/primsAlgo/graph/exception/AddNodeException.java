package lt.refactory.primsAlgo.graph.exception;

public class AddNodeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddNodeException(String msg) {
		super(msg);
	}

	public AddNodeException(String msg, Throwable t) {
		super(msg, t);
	}
}
