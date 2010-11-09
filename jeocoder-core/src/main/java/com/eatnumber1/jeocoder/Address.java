package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public interface Address {
	@NotNull
	String getCountry();

	@NotNull
	String format();

	<T> T visit( @NotNull AddressVisitor<T> visitor );
}
