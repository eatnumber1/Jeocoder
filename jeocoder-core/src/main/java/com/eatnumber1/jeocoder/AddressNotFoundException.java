package com.eatnumber1.jeocoder;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
public class AddressNotFoundException extends GeocodingException {
	public AddressNotFoundException( String s ) {
		super(s);
	}

	public AddressNotFoundException( String s, Throwable throwable ) {
		super(s, throwable);
	}

	public AddressNotFoundException( Throwable throwable ) {
		super(throwable);
	}
}
