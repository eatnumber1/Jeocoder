package com.eatnumber1.jeocoder.google;

import com.eatnumber1.ToStringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class Viewport {
	@NotNull
	private Location southwest, northeast;

	public void setSouthwest( @NotNull Location southwest ) {
		this.southwest = southwest;
	}

	public void setNortheast( @NotNull Location northeast ) {
		this.northeast = northeast;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("southwest", southwest).
				append("northeast", northeast).
				toString();
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof Viewport) ) return false;

		Viewport viewport = (Viewport) o;

		if( !northeast.equals(viewport.northeast) ) return false;
		if( !southwest.equals(viewport.southwest) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = southwest.hashCode();
		result = 31 * result + northeast.hashCode();
		return result;
	}
}
