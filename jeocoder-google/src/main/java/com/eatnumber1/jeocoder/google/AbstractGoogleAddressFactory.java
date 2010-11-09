package com.eatnumber1.jeocoder.google;

import com.eatnumber1.jeocoder.AddressFactory;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
abstract class AbstractGoogleAddressFactory implements AddressFactory {
	@NotNull
	private AddressComponent[] addressComponents;

	AbstractGoogleAddressFactory( @NotNull AddressComponent[] addressComponents ) {
		this.addressComponents = addressComponents;
	}

	@NotNull
	public AddressComponent[] getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents( @NotNull AddressComponent[] addressComponents ) {
		this.addressComponents = addressComponents;
	}

	@NotNull
	protected AddressComponent findComponent( AddressComponentType type ) {
		for( AddressComponent component : addressComponents )
			if( ArrayUtils.contains(component.getTypes(), type) ) return component;
		throw new NoSuchElementException("Component " + type + " not found");
	}
}
