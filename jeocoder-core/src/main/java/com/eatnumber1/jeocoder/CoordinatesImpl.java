package com.eatnumber1.jeocoder;

import com.eatnumber1.ToStringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public class CoordinatesImpl implements Coordinates {
	@NotNull
	private Double latitude;

	@NotNull
	private Double longitude;

	public CoordinatesImpl( @NotNull Double latitude, @NotNull Double longitude ) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@NotNull
	@Override
	public Double getLatitude() {
		return latitude;
	}

	@NotNull
	@Override
	public Double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("latitude", latitude).
				append("longitude", longitude).
				toString();
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof CoordinatesImpl) ) return false;

		CoordinatesImpl that = (CoordinatesImpl) o;

		if( !latitude.equals(that.latitude) ) return false;
		if( !longitude.equals(that.longitude) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = latitude.hashCode();
		result = 31 * result + longitude.hashCode();
		return result;
	}
}
