package com.eatnumber1.jeocoder.google;

import com.eatnumber1.jeocoder.Coordinates;
import com.eatnumber1.jeocoder.CoordinatesFactory;
import com.eatnumber1.jeocoder.CoordinatesImpl;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class GoogleCoordinatesFactory implements CoordinatesFactory {
	@NotNull
	Location location;

	GoogleCoordinatesFactory( @NotNull Location location ) {
		this.location = location;
	}

	@NotNull
	@Override
	public Coordinates produce() {
		return new CoordinatesImpl(location.getLatitude(), location.getLongitude());
	}
}
