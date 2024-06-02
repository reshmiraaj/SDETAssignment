package com.pojo.api;

public class User {

	private int id;
	private Address address;

	public int getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public static class Address {
		private Geo geo;

		public Geo getGeo() {
			return geo;
		}

		public static class Geo {
			private String lat;
			private String lng;

			public String getLat() {
				return lat;
			}

			public String getLng() {
				return lng;
			}
		}
	}
}

