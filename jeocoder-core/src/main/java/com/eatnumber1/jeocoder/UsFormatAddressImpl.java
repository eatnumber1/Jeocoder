package com.eatnumber1.jeocoder;

import com.eatnumber1.ToStringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public class UsFormatAddressImpl extends AddressImpl implements UsFormatAddress {
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

	public UsFormatAddressImpl( String state, String city, String county, String postalCode, String street, String streetNumber ) {
		super("United States");
		this.state = state;
		this.city = city;
		this.county = county;
		this.postalCode = postalCode;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	@Nullable
	@Override
	public String getState() {
		return state;
	}

	@Nullable
	@Override
	public String getCity() {
		return city;
	}

	@Nullable
	@Override
	public String getCounty() {
		return county;
	}

	@Nullable
	@Override
	public String getPostalCode() {
		return postalCode;
	}

	@Nullable
	@Override
	public String getStreetNumber() {
		return streetNumber;
	}

	@Nullable
	public String getStreet() {
		return street;
	}

	@NotNull
	@Override
	public String format() {
		// TODO: Format better if null
		StringBuilder sb = new StringBuilder();
		sb.append(getStreetNumber()).append(' ');
		sb.append(getStreet()).append(' ');
		sb.append(getCity()).append(", ");
		sb.append(getState()).append(' ');
		sb.append(getPostalCode()).append(", ");
		sb.append(getCountry());
		return sb.toString();
	}

	@Override
	public <T> T visit( @NotNull AddressVisitor<T> visitor ) {
		return visitor.visitUsFormatAddress(this);
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof UsFormatAddressImpl) ) return false;
		if( !super.equals(o) ) return false;

		UsFormatAddressImpl usAddress = (UsFormatAddressImpl) o;

		if( city != null ? !city.equals(usAddress.city) : usAddress.city != null ) return false;
		if( county != null ? !county.equals(usAddress.county) : usAddress.county != null ) return false;
		if( streetNumber != null ? !streetNumber.equals(usAddress.streetNumber) : usAddress.streetNumber != null )
			return false;
		if( postalCode != null ? !postalCode.equals(usAddress.postalCode) : usAddress.postalCode != null ) return false;
		if( state != null ? !state.equals(usAddress.state) : usAddress.state != null ) return false;
		if( street != null ? !street.equals(usAddress.street) : usAddress.street != null ) return false;

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
}
