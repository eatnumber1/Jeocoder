package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
public interface GeocodedAddressFactory {
	@NotNull
	GeocodedAddress produce();
}
