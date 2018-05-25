package com.inayelle;

import com.google.gson.Gson;
import org.junit.Test;

public class GSONTest
{
	private String json;
	
	
	@Test
	public void before()
	{
		json = "{  " +
				"    \"title\":\"TITLE228\"," +
				"    \"description\":\"DESCRIPTION228\"," +
				"    \"questionCount\":3," +
				"    \"questions\":[  " +
				"        {  " +
				"            \"id\":\"1\"," +
				"            \"text\":\"Q1\"," +
				"            \"answers\":[  " +
				"                {  " +
				"                    \"id\":1," +
				"                    \"text\":\"A11\"," +
				"                    \"correct\":false" +
				"                }," +
				"                {  " +
				"                    \"id\":2," +
				"                    \"text\":\"A12\"," +
				"                    \"correct\":true" +
				"                }" +
				"            ]" +
				"        }," +
				"        {  " +
				"            \"id\":\"2\"," +
				"            \"text\":\"Q2\"," +
				"            \"answers\":[  " +
				"                {  " +
				"                    \"id\":1," +
				"                    \"text\":\"A21\"," +
				"                    \"correct\":true" +
				"                }," +
				"                {  " +
				"                    \"id\":2," +
				"                    \"text\":\"A22\"," +
				"                    \"correct\":false" +
				"                }," +
				"                {  " +
				"                    \"id\":3," +
				"                    \"text\":\"A23\"," +
				"                    \"correct\":true" +
				"                }" +
				"            ]" +
				"        }," +
				"        {  " +
				"            \"id\":\"3\"," +
				"            \"text\":\"Q3\"," +
				"            \"answers\":[  " +
				"                {  " +
				"                    \"id\":1," +
				"                    \"text\":\"A31\"," +
				"                    \"correct\":false" +
				"                }," +
				"                {  " +
				"                    \"id\":2," +
				"                    \"text\":\"A32\"," +
				"                    \"correct\":true" +
				"                }," +
				"                {  " +
				"                    \"id\":3," +
				"                    \"text\":\"A33\"," +
				"                    \"correct\":true" +
				"                }" +
				"            ]" +
				"        }" +
				"    ]" +
				"}";
	}
	
	@Test
	public void testGSON()
	{
		var gson = new Gson();
		
		var json = "{\"tid\":1,\"questions\":[{\"id\":1,\"answers\":[{\"id\":1,\"getIsCorrect\":false},{\"id\":2,\"getIsCorrect\":true}]},{\"id\":2," +
				"\"answers\":[{\"id\":1,\"getIsCorrect\":true},{\"id\":2,\"getIsCorrect\":true}]}]}";
		
		var test = gson.fromJson(json, com.inayelle.model.entity.Test.class);
		
		System.out.println(test);
	}
	
	
}
