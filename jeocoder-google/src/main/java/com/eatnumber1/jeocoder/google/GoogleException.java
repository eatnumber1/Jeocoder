package com.eatnumber1.jeocoder.google;

import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
public class GoogleException extends Exception {
	@NotNull
	private GoogleStatusCode statusCode;

	public GoogleException( @NotNull GoogleStatusCode statusCode ) {
		this.statusCode = statusCode;
	}

	public GoogleException( String s, @NotNull GoogleStatusCode statusCode ) {
		super(s);
		this.statusCode = statusCode;
	}

	public GoogleException( String s, Throwable throwable, @NotNull GoogleStatusCode statusCode ) {
		super(s, throwable);
		this.statusCode = statusCode;
	}

	public GoogleException( Throwable throwable, @NotNull GoogleStatusCode statusCode ) {
		super(throwable);
		this.statusCode = statusCode;
	}

	@NotNull
	public GoogleStatusCode getStatusCode() {
		return statusCode;
	}
}
