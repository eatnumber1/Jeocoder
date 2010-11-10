package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.jeocoder.CoordinatesImpl;
import com.eatnumber1.jeocoder.GeocodedAddress;
import com.eatnumber1.jeocoder.GeocodedAddressImpl;
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

import java.io.Serializable;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
public class TestHibernateGeocodedAddressFactory {
	@NotNull
	private static Log log = LogFactory.getLog(TestHibernateGeocodedAddressFactory.class);

	@NotNull
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory( @NotNull SessionFactory sessionFactory ) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Test
	public void saveAndLoad() {
		GeocodedAddress geocodedAddress = new GeocodedAddressImpl(new UsFormatAddressImpl("CA", "Mountain View", null, null, "Amphitheatre Parkway", "1600"), new CoordinatesImpl(10.0, 10.0));
		HibernateGeocodedAddressFactory factory = new HibernateGeocodedAddressFactory(geocodedAddress);
		Serializable savedId = hibernateTemplate.save(factory);
		log.debug("Saved " + factory + " to database");
		HibernateGeocodedAddressFactory savedFactory = (HibernateGeocodedAddressFactory) hibernateTemplate.get(HibernateGeocodedAddressFactory.class, savedId);
		log.debug("Read " + savedFactory + " from database");
		Assert.assertThat(geocodedAddress, is(savedFactory.produce()));
	}
}
