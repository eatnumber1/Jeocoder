package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.ToStringUtils;
import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.GeocodedAddress;
import com.eatnumber1.jeocoder.Geocoder;
import com.eatnumber1.jeocoder.GeocodingException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.jetbrains.annotations.NotNull;
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

	@NotNull
	private Geocoder dataSource;

	public HibernateGeocoder( @NotNull HibernateTemplate hibernateTemplate, @NotNull Geocoder dataSource ) {
		this.hibernateTemplate = hibernateTemplate;
		this.dataSource = dataSource;
	}

	@NotNull
	@Override
	public Set<GeocodedAddress> geocode( @NotNull Address address ) throws GeocodingException {
		DetachedCriteria criteria = DetachedCriteria.forClass(HibernateGeocodedAddressFactory.class).createCriteria("addressFactory");
		criteria.add(Example.create(new HibernateAddressFactory(address)));
		@SuppressWarnings({ "unchecked" })
		List<HibernateGeocodedAddressFactory> results = hibernateTemplate.findByCriteria(criteria);
		if( results.size() == 0 ) {
			log.debug("No saved address found. Falling back to " + dataSource);
			Set<GeocodedAddress> geocodedAddresses = dataSource.geocode(address);
			for( GeocodedAddress geocodedAddress : geocodedAddresses )
				hibernateTemplate.save(new HibernateGeocodedAddressFactory(geocodedAddress));
			return geocodedAddresses;
		}
		Set<GeocodedAddress> retval = new HashSet<GeocodedAddress>(results.size());
		for( HibernateGeocodedAddressFactory factory : results ) retval.add(factory.produce());
		log.debug("Geocoded address " + address + " to " + retval);
		return retval;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("hibernateTemplate", hibernateTemplate).
				append("dataSource", dataSource).
				toString();
	}
}
