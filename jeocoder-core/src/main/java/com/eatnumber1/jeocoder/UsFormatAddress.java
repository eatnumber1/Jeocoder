package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.Nullable;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public interface UsFormatAddress extends Address {
	@Nullable
	String getState();

	@Nullable
	String getCity();

	@Nullable
	String getCounty();

	@Nullable
	String getPostalCode();

	@Nullable
	String getStreetNumber();

	@Nullable
	String getStreet();
}
