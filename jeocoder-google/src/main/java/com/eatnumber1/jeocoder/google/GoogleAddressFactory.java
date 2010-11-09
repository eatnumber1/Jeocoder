package com.eatnumber1.jeocoder.google;

import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.AddressFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
class GoogleAddressFactory extends AbstractGoogleAddressFactory {
	GoogleAddressFactory( @NotNull AddressComponent[] addressComponents ) {
		super(addressComponents);
	}

	@NotNull
	@Override
	public Address produce() {
		AddressFactory factory;
		String short_name = findComponent(AddressComponentType.country).getShortName();
		if( !"US".equals(short_name) )
			throw new UnsupportedOperationException("Unsupported country " + short_name);
		factory = new GoogleUsAddressFactory(getAddressComponents());
		return factory.produce();
	}
}
