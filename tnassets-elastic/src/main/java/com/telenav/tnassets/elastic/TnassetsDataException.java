package com.telenav.tnassets.elastic;

/**
 * RocketFlowDataException.
 *
 * @author jamie
 *
 */
public class TnassetsDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public TnassetsDataException(String message) {
		super(message);
	}

	public TnassetsDataException(String message, Exception root) {
		super(root);
	}
}
