package com.eatnumber1.jeocoder.google;

import com.eatnumber1.ToStringUtils;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class Result {
	@NotNull
	private AddressComponentType[] types;

	@NotNull
	@SerializedName("formatted_address")
	private String formattedAddress;

	@NotNull
	@SerializedName("address_components")
	private AddressComponent[] addressComponents;

	@NotNull
	private Geometry geometry;

	@Nullable
	@SerializedName("partial_match")
	private Boolean partialMatch;

	public void setTypes( @NotNull AddressComponentType[] types ) {
		this.types = types;
	}

	public void setFormattedAddress( @NotNull String formattedAddress ) {
		this.formattedAddress = formattedAddress;
	}

	public void setAddressComponents( @NotNull AddressComponent[] addressComponents ) {
		this.addressComponents = addressComponents;
	}

	public void setGeometry( @NotNull Geometry geometry ) {
		this.geometry = geometry;
	}

	public void setPartialMatch( @Nullable Boolean partialMatch ) {
		this.partialMatch = partialMatch;
	}

	@NotNull
	public AddressComponentType[] getTypes() {
		return types;
	}

	@NotNull
	public String getFormattedAddress() {
		return formattedAddress;
	}

	@NotNull
	public AddressComponent[] getAddressComponents() {
		return addressComponents;
	}

	@NotNull
	public Geometry getGeometry() {
		return geometry;
	}

	@Nullable
	public Boolean isPartialMatch() {
		return partialMatch;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("types", types).
				append("formattedAddress", formattedAddress).
				append("addressComponents", addressComponents).
				append("geometry", geometry).
				append("partialMatch", partialMatch).
				toString();
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof Result) ) return false;

		Result result = (Result) o;

		if( !Arrays.equals(addressComponents, result.addressComponents) ) return false;
		if( !formattedAddress.equals(result.formattedAddress) ) return false;
		if( !geometry.equals(result.geometry) ) return false;
		if( partialMatch != null ? !partialMatch.equals(result.partialMatch) : result.partialMatch != null )
			return false;
		if( !Arrays.equals(types, result.types) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(types);
		result = 31 * result + formattedAddress.hashCode();
		result = 31 * result + Arrays.hashCode(addressComponents);
		result = 31 * result + geometry.hashCode();
		result = 31 * result + (partialMatch != null ? partialMatch.hashCode() : 0);
		return result;
	}
}
