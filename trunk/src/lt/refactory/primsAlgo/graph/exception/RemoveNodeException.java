package lt.refactory.primsAlgo.graph.exception;

public class RemoveNodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 110688246999668971L;

	public RemoveNodeException(String msg) {
		super(msg);
	}

	public RemoveNodeException(String msg, Throwable t) {
		super(msg, t);
	}
}
