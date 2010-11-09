package com.eatnumber1.jeocoder.google;

import com.eatnumber1.ToStringUtils;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class AddressComponent {
	@NotNull
	private AddressComponentType[] types;

	@NotNull
	@SerializedName("long_name")
	private String longName;

	@NotNull
	@SerializedName("short_name")
	private String shortName;

	public void setTypes( @NotNull AddressComponentType[] types ) {
		this.types = types;
	}

	public void setLongName( @NotNull String longName ) {
		this.longName = longName;
	}

	public void setShortName( @NotNull String shortName ) {
		this.shortName = shortName;
	}

	@NotNull
	public AddressComponentType[] getTypes() {
		return types;
	}

	@NotNull
	public String getLongName() {
		return longName;
	}

	@NotNull
	public String getShortName() {
		return shortName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("types", types).
				append("longName", longName).
				append("shortName", shortName).
				toString();
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof AddressComponent) ) return false;

		AddressComponent component = (AddressComponent) o;

		if( !longName.equals(component.longName) ) return false;
		if( !shortName.equals(component.shortName) ) return false;
		if( !Arrays.equals(types, component.types) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(types);
		result = 31 * result + longName.hashCode();
		result = 31 * result + shortName.hashCode();
		return result;
	}
}
