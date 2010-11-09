package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
public class GeocodedAddressFactoryImpl implements GeocodedAddressFactory {
	@NotNull
	private AddressFactory addressFactory;

	@NotNull
	private CoordinatesFactory coordinatesFactory;

	public GeocodedAddressFactoryImpl( @NotNull AddressFactory addressFactory, @NotNull CoordinatesFactory coordinatesFactory ) {
		this.addressFactory = addressFactory;
		this.coordinatesFactory = coordinatesFactory;
	}

	@NotNull
	@Override
	public GeocodedAddress produce() {
		return new GeocodedAddressImpl(addressFactory.produce(), coordinatesFactory.produce());
	}
}
