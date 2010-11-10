package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.AddressNotFoundException;
import com.eatnumber1.jeocoder.GeocodedAddress;
import com.eatnumber1.jeocoder.Geocoder;
import com.eatnumber1.jeocoder.GeocodingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
public class HibernateGeocoder implements Geocoder {
	@NotNull
	private static Log log = LogFactory.getLog(HibernateGeocoder.class);

	@NotNull
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public HibernateGeocoder( @NotNull SessionFactory sessionFactory ) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@NotNull
	@Override
	public Set<GeocodedAddress> geocode( @NotNull Address address ) throws GeocodingException {
		DetachedCriteria criteria = DetachedCriteria.forClass(HibernateGeocodedAddressFactory.class).createCriteria("addressFactory");
		criteria.add(Example.create(new HibernateAddressFactory(address)));
		@SuppressWarnings({ "unchecked" })
		List<HibernateGeocodedAddressFactory> results = hibernateTemplate.findByCriteria(criteria);
		if( results.size() == 0 ) throw new AddressNotFoundException("No saved address found");
		Set<GeocodedAddress> retval = new HashSet<GeocodedAddress>(results.size());
		for( HibernateGeocodedAddressFactory factory : results ) retval.add(factory.produce());
		log.debug("Geocoded address " + address + " to " + retval);
		return retval;
	}
}
