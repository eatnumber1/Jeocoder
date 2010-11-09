package com.eatnumber1.jeocoder;

import com.eatnumber1.ToStringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
public class AddressImpl implements Address {
	@NotNull
	private String country;

	public AddressImpl( @NotNull String country ) {
		this.country = country;
	}

	@NotNull
	@Override
	public String getCountry() {
		return country;
	}

	@NotNull
	@Override
	public String format() {
		return country;
	}

	@Override
	public <T> T visit( @NotNull AddressVisitor<T> visitor ) {
		return visitor.visitAddress(this);
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof AddressImpl) ) return false;

		AddressImpl address = (AddressImpl) o;

		return country.equals(address.country);

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
