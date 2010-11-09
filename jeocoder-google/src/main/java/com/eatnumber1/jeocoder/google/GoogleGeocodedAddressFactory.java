package com.eatnumber1.jeocoder.google;

import com.eatnumber1.jeocoder.GeocodedAddressFactoryImpl;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class GoogleGeocodedAddressFactory extends GeocodedAddressFactoryImpl {
	GoogleGeocodedAddressFactory( @NotNull Result result ) {
		super(new GoogleAddressFactory(result.getAddressComponents()), new GoogleCoordinatesFactory(result.getGeometry().getLocation()));
	}
}
