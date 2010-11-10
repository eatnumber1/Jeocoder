package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.CoordinatesImpl;
import com.eatnumber1.jeocoder.GeocodedAddress;
import com.eatnumber1.jeocoder.GeocodedAddressImpl;
import com.eatnumber1.jeocoder.GeocodingException;
import com.eatnumber1.jeocoder.UsFormatAddressImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Russell Harmon
 * @since Nov 12, 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
public class TestHibernateGeocoder {
	@NotNull
	private static Log log = LogFactory.getLog(TestHibernateGeocoder.class);

	@NotNull
	private HibernateTemplate hibernateTemplate;

	@NotNull
	private HibernateGeocoder geocoder;

	@Autowired
	public void setSessionFactory( @NotNull SessionFactory sessionFactory ) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
		geocoder = new HibernateGeocoder(sessionFactory);
	}

	@Test
	public void lookup() throws GeocodingException {
		Address address = new UsFormatAddressImpl("CA", "Mountain View", null, null, "Amphitheatre Parkway", "1600");
		GeocodedAddress preGeocodedAddress = new GeocodedAddressImpl(address, new CoordinatesImpl(10.0, 10.0));
		HibernateGeocodedAddressFactory factory = new HibernateGeocodedAddressFactory(preGeocodedAddress);
		hibernateTemplate.save(factory);
		log.debug("Pre-loaded database with geocoded address " + preGeocodedAddress);

		Set<GeocodedAddress> geocodedAddresses = geocoder.geocode(address);
		Assert.assertThat(geocodedAddresses.size(), is(1));
		Assert.assertThat(geocodedAddresses.iterator().next(), is(preGeocodedAddress));
	}
}
