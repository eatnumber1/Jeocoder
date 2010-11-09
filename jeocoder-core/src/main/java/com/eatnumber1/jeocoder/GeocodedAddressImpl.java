package com.eatnumber1.jeocoder;

import com.eatnumber1.ToStringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public class GeocodedAddressImpl implements GeocodedAddress {
	@NotNull
	private Address address;

	@NotNull
	private Coordinates coordinates;

	public GeocodedAddressImpl() {
	}

	public GeocodedAddressImpl( @NotNull Address address, @NotNull Coordinates coordinates ) {
		this.address = address;
		this.coordinates = coordinates;
	}

	@NotNull
	@Override
	public Coordinates getCoordinates() {
		return coordinates;
	}

	@NotNull
	public Address getAddress() {
		return address;
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof GeocodedAddressImpl) ) return false;

		GeocodedAddressImpl that = (GeocodedAddressImpl) o;

		if( !address.equals(that.address) ) return false;
		if( !coordinates.equals(that.coordinates) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = address.hashCode();
		result = 31 * result + coordinates.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("address", address).
				append("coordinates", coordinates).
				toString();
	}
}
