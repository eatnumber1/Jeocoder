package com.eatnumber1.jeocoder;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public class GeocodingException extends Exception {
	public GeocodingException( String s ) {
		super(s);
	}

	public GeocodingException( String s, Throwable throwable ) {
		super(s, throwable);
	}

	public GeocodingException( Throwable throwable ) {
		super(throwable);
	}
}
