package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public class AbstractAddressVisitor<T> implements AddressVisitor<T> {
	@Override
	public T visitUsAddress( @NotNull UsFormatAddress address ) {
		return null;
	}

	@Override
	public T visitAddress( @NotNull Address address ) {
		return null;
	}
}
