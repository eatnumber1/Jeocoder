package com.eatnumber1.jeocoder;

import com.eatnumber1.jeocoder.google.GoogleGeocoder;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
public class TestGoogleGeocoder {
	private GoogleGeocoder geocoder;

	@Before
	public void setUp() {
		geocoder = new GoogleGeocoder(false);
	}

	@Test
	public void singleAddress() throws GeocodingException {
		UsFormatAddress address = new UsFormatAddressImpl("CA", "Mountain View", null, null, "Amphitheatre Parkway", "1600");
		Set<GeocodedAddress> geoAddresses = geocoder.geocode(address);
		Assert.assertEquals("Too many results", 1, geoAddresses.size());
		GeocodedAddress geoAddress = geoAddresses.iterator().next();
		Assert.assertEquals("Incorrect latitude", 37.4227820, geoAddress.getCoordinates().getLatitude(), 0);
		Assert.assertEquals("Incorrect longitude", -122.0850990, geoAddress.getCoordinates().getLongitude(), 0);
		Assert.assertEquals("United States", geoAddress.getAddress().getCountry());
		UsFormatAddress usAddress = geoAddress.getAddress().visit(new AbstractAddressVisitor<UsFormatAddress>() {
			@Override
			public UsFormatAddress visitUsFormatAddress( @NotNull UsFormatAddress address ) {
				return address;
			}
		});
		Assert.assertNotNull("Address is not a US format address", usAddress);
		Assert.assertEquals("California", usAddress.getState());
		Assert.assertEquals("Santa Clara", usAddress.getCounty());
		Assert.assertEquals("Mountain View", usAddress.getCity());
		Assert.assertEquals("Amphitheatre Pkwy", usAddress.getStreet());
		Assert.assertEquals("1600", usAddress.getStreetNumber());
		Assert.assertEquals("94043", usAddress.getPostalCode());
	}
}
