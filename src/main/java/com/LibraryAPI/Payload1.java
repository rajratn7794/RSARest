package com.LibraryAPI;

public class Payload1 {

	
		// TODO Auto-generated method stub
		
		public static String addbook( String isbn,String aisle)
		{
			String add="{\r\n"
					+ "    \"name\":\"abc book\",\r\n"
					+ "    \"isbn\":\""+isbn+"\",\r\n"
					+ "    \"aisle\":\""+aisle+"\",\r\n"
					+ "    \"author\":\"john\"\r\n"
					+ "\r\n"
					+ "}";
			return add;
		}

	}


