package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public interface AddressVisitor<T> {
	T visitAddress( @NotNull Address address );

	T visitUsAddress( @NotNull UsFormatAddress address );
}
