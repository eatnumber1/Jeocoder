package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.ToStringUtils;
import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.AddressFactory;
import com.eatnumber1.jeocoder.AddressVisitor;
import com.eatnumber1.jeocoder.CoordinatesFactory;
import com.eatnumber1.jeocoder.GeocodedAddress;
import com.eatnumber1.jeocoder.GeocodedAddressFactory;
import com.eatnumber1.jeocoder.GeocodedAddressImpl;
import com.eatnumber1.jeocoder.UsFormatAddress;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.jetbrains.annotations.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
@Entity
class HibernateGeocodedAddressFactory implements GeocodedAddressFactory {
	@NotNull
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ForeignKey(name = "id")
	@OneToOne(cascade = CascadeType.ALL)
	private HibernateAddressFactory addressFactory;

	@NotNull
	@ForeignKey(name = "id")
	@OneToOne(cascade = CascadeType.ALL)
	private HibernateCoordinatesFactory coordinatesFactory;

	public HibernateGeocodedAddressFactory() {
	}

	public HibernateGeocodedAddressFactory( @NotNull GeocodedAddress address ) {
		addressFactory = address.getAddress().visit(new AddressVisitor<HibernateAddressFactory>() {
			@Override
			public HibernateAddressFactory visitUsFormatAddress( @NotNull UsFormatAddress address ) {
				return new HibernateUsFormatAddressFactory(address);
			}

			@Override
			public HibernateAddressFactory visitAddress( @NotNull Address address ) {
				return new HibernateAddressFactory(address);
			}
		});
		coordinatesFactory = new HibernateCoordinatesFactory(address.getCoordinates());
	}

	@NotNull
	public AddressFactory getAddressFactory() {
		return addressFactory;
	}

	public void setAddressFactory( @NotNull HibernateAddressFactory addressFactory ) {
		this.addressFactory = addressFactory;
	}

	@NotNull
	public CoordinatesFactory getCoordinatesFactory() {
		return coordinatesFactory;
	}

	public void setCoordinatesFactory( @NotNull HibernateCoordinatesFactory coordinatesFactory ) {
		this.coordinatesFactory = coordinatesFactory;
	}

	@NotNull
	public Long getId() {
		return id;
	}

	public void setId( @NotNull Long id ) {
		this.id = id;
	}

	@NotNull
	@Override
	public GeocodedAddress produce() {
		return new GeocodedAddressImpl(addressFactory.produce(), coordinatesFactory.produce());
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof HibernateGeocodedAddressFactory) ) return false;

		HibernateGeocodedAddressFactory that = (HibernateGeocodedAddressFactory) o;

		if( !addressFactory.equals(that.addressFactory) ) return false;
		if( !coordinatesFactory.equals(that.coordinatesFactory) ) return false;
		if( !id.equals(that.id) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + addressFactory.hashCode();
		result = 31 * result + coordinatesFactory.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("id", id).
				append("addressFactory", addressFactory).
				append("coordinatesFactory", coordinatesFactory).
				toString();
	}
}
