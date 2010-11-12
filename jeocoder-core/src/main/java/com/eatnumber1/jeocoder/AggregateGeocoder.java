package com.eatnumber1.jeocoder;

import com.eatnumber1.ToStringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
public class AggregateGeocoder implements Geocoder {
	@NotNull
	private List<Geocoder> delegates;

	public AggregateGeocoder() {
		delegates = new ArrayList<Geocoder>();
	}

	public AggregateGeocoder( @NotNull List<Geocoder> delegates ) {
		this.delegates = delegates;
	}

	@NotNull
	public List<Geocoder> getDelegates() {
		return delegates;
	}

	public void setDelegates( @NotNull List<Geocoder> delegates ) {
		this.delegates = delegates;
	}

	@NotNull
	@Override
	public Set<GeocodedAddress> geocode( @NotNull Address address ) throws GeocodingException {
		if( delegates.size() == 0 ) throw new GeocodingException("No delegates defined");
		AddressNotFoundException ex = null;
		for( Geocoder geocoder : delegates ) {
			try {
				return geocoder.geocode(address);
			} catch( AddressNotFoundException e ) {
				ex = e;
			}
		}
		assert (ex != null);
		throw ex;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("delegates", delegates).
				toString();
	}
}
