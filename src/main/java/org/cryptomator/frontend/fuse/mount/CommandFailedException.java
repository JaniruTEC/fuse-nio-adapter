package org.cryptomator.frontend.fuse.mount;

public class CommandFailedException extends RuntimeException {

	public CommandFailedException(String message) {
		super(message);
	}

	public CommandFailedException(Throwable cause) {
		super(cause);
	}

}
