package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.ToStringUtils;
import com.eatnumber1.jeocoder.Coordinates;
import com.eatnumber1.jeocoder.CoordinatesFactory;
import com.eatnumber1.jeocoder.CoordinatesImpl;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
@Entity
class HibernateCoordinatesFactory implements CoordinatesFactory {
	@NotNull
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private Double latitude;

	@NotNull
	private Double longitude;

	public HibernateCoordinatesFactory() {
	}

	public HibernateCoordinatesFactory( @NotNull Coordinates coordinates ) {
		latitude = coordinates.getLatitude();
		longitude = coordinates.getLongitude();
	}

	@NotNull
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude( @NotNull Double longitude ) {
		this.longitude = longitude;
	}

	@NotNull
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude( @NotNull Double latitude ) {
		this.latitude = latitude;
	}

	@NotNull
	public Long getId() {
		return id;
	}

	public void setId( @NotNull Long id ) {
		this.id = id;
	}

	@NotNull
	@Override
	public Coordinates produce() {
		return new CoordinatesImpl(latitude, longitude);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringUtils.STYLE).
				append("id", id).
				append("latitude", latitude).
				append("longitude", longitude).
				toString();
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !(o instanceof HibernateCoordinatesFactory) ) return false;

		HibernateCoordinatesFactory that = (HibernateCoordinatesFactory) o;

		if( !latitude.equals(that.latitude) ) return false;
		if( !longitude.equals(that.longitude) ) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = latitude.hashCode();
		result = 31 * result + longitude.hashCode();
		return result;
	}
}
