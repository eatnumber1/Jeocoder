package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.ToStringUtils;
import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.UsFormatAddress;
import com.eatnumber1.jeocoder.UsFormatAddressImpl;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
@Entity
class HibernateUsFormatAddressFactory extends HibernateAddressFactory {
	@Nullable
	private String state;

	@Nullable
	private String city;

	@Nullable
	private String county;

	@Nullable
	private String postalCode;

	@Nullable
	private String street;

	@Nullable
	private String streetNumber;

	public HibernateUsFormatAddressFactory() {
	}

	public HibernateUsFormatAddressFactory( @NotNull UsFormatAddress address ) {
		super(address);
		state = address.getState();
		city = address.getCity();
		county = address.getCounty();
		postalCode = address.getPostalCode();
		street = address.getStreet();
		streetNumber = address.getStreetNumber();
	}

	@NotNull
	@Override
	public Address produce() {
		return new UsFormatAddressImpl(state, city, county, postalCode, street, streetNumber);
	}

	@Nullable
	public String getState() {
		return state;
	}

	public void setState( @Nullable String state ) {
		this.state = state;
	}

	@Nullable
	public String getCity() {
		return city;
	}

	public void setCity( @Nullable String city ) {
		this.city = city;
	}

	@Nullable
	public String getCounty() {
		return county;
	}

	public void setCounty( @Nullable String county ) {
		this.county = county;
	}

	@Nullable
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode( @Nullable String postalCode ) {
		this.postalCode = postalCode;
	}

	@Nullable
	public String getStreet() {
		return street;
	}

	public void setStreet( @Nullable String street ) {
		this.street = street;
	}

	@Nullable
	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber( @Nullable String streetNumber ) {
		this.streetNumber = streetNumber;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("state", state).
				append("city", city).
				append("county", county).
				append("postalCode", postalCode).
				append("street", street).
				append("streetNumber", streetNumber).
				appendSuper(super.toString()).
				toString();
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof HibernateUsFormatAddressFactory) ) return false;
		if( !super.equals(o) ) return false;

		HibernateUsFormatAddressFactory that = (HibernateUsFormatAddressFactory) o;

		if( city != null ? !city.equals(that.city) : that.city != null ) return false;
		if( county != null ? !county.equals(that.county) : that.county != null ) return false;
		if( postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null ) return false;
		if( state != null ? !state.equals(that.state) : that.state != null ) return false;
		if( street != null ? !street.equals(that.street) : that.street != null ) return false;
		if( streetNumber != null ? !streetNumber.equals(that.streetNumber) : that.streetNumber != null ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (county != null ? county.hashCode() : 0);
		result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
		result = 31 * result + (street != null ? street.hashCode() : 0);
		result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
		return result;
	}
}
