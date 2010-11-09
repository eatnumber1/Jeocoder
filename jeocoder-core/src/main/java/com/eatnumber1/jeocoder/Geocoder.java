package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public interface Geocoder {
	@NotNull
	Set<GeocodedAddress> geocode( @NotNull String address ) throws GeocodingException;

	@NotNull
	Set<GeocodedAddress> geocode( @NotNull Address address ) throws GeocodingException;
}
