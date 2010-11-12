package com.eatnumber1.jeocoder;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Russell Harmon
 * @since Nov 12, 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "spring-config.xml")
public class TestAggregateGeocoder {
	@NotNull
	@Resource(name = "hibernateGeocoder")
	private Geocoder geocoder;

	@Test
	public void geocode() throws GeocodingException {
		Address address = new UsFormatAddressImpl("CA", "Mountain View", null, null, "Amphitheatre Parkway", "1600");
		Set<GeocodedAddress> googleGeocoded = geocoder.geocode(address);
		Set<GeocodedAddress> hibernateGeocoded = geocoder.geocode(address);
		Assert.assertThat(googleGeocoded, is(hibernateGeocoded));
	}
}
