package com.eatnumber1;

import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
public class ToStringUtils {
	@NotNull
	public static final ToStringStyle STYLE;

	static {
		StandardToStringStyle style = new StandardToStringStyle();
		style.setUseShortClassName(true);
		style.setUseIdentityHashCode(false);
		style.setContentStart("{");
		style.setContentEnd("}");
		STYLE = style;
	}
}
