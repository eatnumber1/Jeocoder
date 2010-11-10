package com.eatnumber1.jeocoder;

import net.jcip.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 8, 2010
 */
@Immutable
public interface Coordinates {
	@NotNull
	Double getLatitude();

	@NotNull
	Double getLongitude();
}
