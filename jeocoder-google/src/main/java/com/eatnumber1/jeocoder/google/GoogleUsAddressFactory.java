package com.eatnumber1.jeocoder.google;

import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.UsFormatAddressImpl;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class GoogleUsAddressFactory extends AbstractGoogleAddressFactory {
	public GoogleUsAddressFactory( @NotNull AddressComponent[] addressComponents ) {
		super(addressComponents);
	}

	@NotNull
	@Override
	public Address produce() {
		return new UsFormatAddressImpl(
				findComponent(AddressComponentType.administrative_area_level_1).getLongName(),
				findComponent(AddressComponentType.locality).getLongName(),
				findComponent(AddressComponentType.administrative_area_level_2).getLongName(),
				findComponent(AddressComponentType.postal_code).getLongName(),
				findComponent(AddressComponentType.route).getLongName(),
				findComponent(AddressComponentType.street_number).getLongName()
		);
	}
}
