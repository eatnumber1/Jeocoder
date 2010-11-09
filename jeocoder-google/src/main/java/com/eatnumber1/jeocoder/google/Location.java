package com.eatnumber1.jeocoder.google;

import com.eatnumber1.ToStringUtils;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class Location {
	@NotNull
	@SerializedName("lat")
	private Double latitude;

	@NotNull
	@SerializedName("lng")
	private Double longitude;

	public void setLatitude( @NotNull Double latitude ) {
		this.latitude = latitude;
	}

	public void setLongitude( @NotNull Double longitude ) {
		this.longitude = longitude;
	}

	@NotNull
	public Double getLatitude() {
		return latitude;
	}

	@NotNull
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
		if( !(o instanceof Location) ) return false;

		Location location = (Location) o;

		if( !latitude.equals(location.latitude) ) return false;
		if( !longitude.equals(location.longitude) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = latitude.hashCode();
		result = 31 * result + longitude.hashCode();
		return result;
	}
}
