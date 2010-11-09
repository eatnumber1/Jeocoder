package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public interface GeocodedAddress {
	@NotNull
	Coordinates getCoordinates();

	@NotNull
	Address getAddress();
}
