package com.eatnumber1.jeocoder.google;

import com.eatnumber1.ToStringUtils;
import com.eatnumber1.jeocoder.Address;
import com.eatnumber1.jeocoder.GeocodedAddress;
import com.eatnumber1.jeocoder.Geocoder;
import com.eatnumber1.jeocoder.GeocodingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Russell Harmon
 * @since Nov 9, 2010
 */
public class GoogleGeocoder implements Geocoder {
	@NotNull
	private static Log log = LogFactory.getLog(GoogleGeocoder.class);

	@NotNull
	private static URI resolveUri;

	static {
		try {
			resolveUri = new URI("http://maps.googleapis.com/maps/api/geocode/json", true);
		} catch( URIException e ) {
			throw new RuntimeException(e);
		}
	}

	@NotNull
	private Boolean sensor;

	public GoogleGeocoder( @NotNull Boolean sensor ) {
		this.sensor = sensor;
	}

	@NotNull
	public Boolean getSensor() {
		return sensor;
	}

	public void setSensor( @NotNull Boolean sensor ) {
		this.sensor = sensor;
	}

	@NotNull
	@Override
	public Set<GeocodedAddress> geocode( @NotNull Address address ) throws GeocodingException {
		log.debug("Geocoding address " + address);
		HttpMethod method = new GetMethod(resolveUri.getEscapedURI());
		method.setQueryString(new NameValuePair[] {
				new NameValuePair("sensor", sensor.toString()),
				new NameValuePair("address", address.format())
		});
		@Nullable
		Reader data = null;
		try {
			HttpClient client = new HttpClient();
			int status;
			status = client.executeMethod(method);
			if( status != HttpStatus.SC_OK ) throw new GeocodingException(HttpStatus.getStatusText(status));
			data = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			GsonBuilder gsonBuilder = new GsonBuilder();
			Type type = new TypeToken<Set<GeocodedAddress>>() {
			}.getType();
			gsonBuilder.registerTypeAdapter(type, new GeocodedAddressDeserializer());
			Set<GeocodedAddress> addresses = gsonBuilder.create().fromJson(data, type);
			data.close();
			log.debug("Geocoded addresses: " + addresses);
			return addresses;
		} catch( RuntimeException e ) {
			if( data != null ) IOUtils.closeQuietly(data);
			throw e;
		} catch( IOException e ) {
			if( data != null ) IOUtils.closeQuietly(data);
			throw new GeocodingException(e);
		} finally {
			method.releaseConnection();
		}
	}

	private static class GeocodedAddressDeserializer implements JsonDeserializer<Set<GeocodedAddress>> {
		@Override
		@NotNull
		public Set<GeocodedAddress> deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
			JsonObject obj = json.getAsJsonObject();
			GoogleStatusCode code = GoogleStatusCode.valueOf(obj.getAsJsonPrimitive("status").getAsString());
			if( code != GoogleStatusCode.OK ) //noinspection ThrowableInstanceNeverThrown
				throw new JsonParseException(new GoogleException(code));
			JsonArray array = obj.getAsJsonArray("results");
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(LocationType.class, new LocationTypeDeserializer());
			Gson gson = gsonBuilder.create();
			List<Result> results = new LinkedList<Result>();
			for( JsonElement elem : array ) results.add(gson.fromJson(elem, Result.class));
			Set<GeocodedAddress> addresses = new HashSet<GeocodedAddress>(results.size());
			for( Result r : results ) {
				addresses.add(new GoogleGeocodedAddressFactory(r).produce());
			}
			return addresses;
		}

		private static class LocationTypeDeserializer implements JsonDeserializer<LocationType> {
			@Override
			@NotNull
			public LocationType deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
				return LocationType.valueOf(json.getAsString());
			}
		}

	}

}
