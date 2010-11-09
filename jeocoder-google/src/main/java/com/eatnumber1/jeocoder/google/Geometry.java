package com.eatnumber1.jeocoder.google;

import com.eatnumber1.ToStringUtils;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class Geometry {
	@NotNull
	private Location location;

	@NotNull
	@SerializedName("location_type")
	private LocationType locationType;

	@NotNull
	private Viewport viewport;

	@NotNull
	private Bounds bounds;

	public void setLocation( @NotNull Location location ) {
		this.location = location;
	}

	public void setLocationType( @NotNull LocationType locationType ) {
		this.locationType = locationType;
	}

	public void setViewport( @NotNull Viewport viewport ) {
		this.viewport = viewport;
	}

	public void setBounds( @NotNull Bounds bounds ) {
		this.bounds = bounds;
	}

	@NotNull
	public Location getLocation() {
		return location;
	}

	@NotNull
	public LocationType getLocationType() {
		return locationType;
	}

	@NotNull
	public Viewport getViewport() {
		return viewport;
	}

	@NotNull
	public Bounds getBounds() {
		return bounds;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("location", location).
				append("locationType", locationType).
				append("viewport", viewport).
				append("bounds", bounds).
				toString();
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof Geometry) ) return false;

		Geometry geometry = (Geometry) o;

		if( !bounds.equals(geometry.bounds) ) return false;
		if( !location.equals(geometry.location) ) return false;
		if( locationType != geometry.locationType ) return false;
		if( !viewport.equals(geometry.viewport) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = location.hashCode();
		result = 31 * result + locationType.hashCode();
		result = 31 * result + viewport.hashCode();
		result = 31 * result + bounds.hashCode();
		return result;
	}
}
