package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.ToStringUtils;
import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.AddressFactory;
import com.eatnumber1.jeocoder.AddressImpl;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
class HibernateAddressFactory implements AddressFactory {
	@NotNull
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String country;

	public HibernateAddressFactory() {
	}

	public HibernateAddressFactory( @NotNull Address address ) {
		country = address.getCountry();
	}

	@NotNull
	public String getCountry() {
		return country;
	}

	public void setCountry( @NotNull String country ) {
		this.country = country;
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
	public Address produce() {
		return new AddressImpl(country);
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof HibernateAddressFactory) ) return false;

		HibernateAddressFactory that = (HibernateAddressFactory) o;

		if( !country.equals(that.country) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return country.hashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("country", country).
				toString();
	}
}
