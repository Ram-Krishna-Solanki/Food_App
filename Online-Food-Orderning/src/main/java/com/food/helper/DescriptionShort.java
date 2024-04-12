package com.food.helper;

public class DescriptionShort {

	public static String get10Words(String description)
	{
	
		if(!description.isEmpty())
		{
		String[] strs = description.split(" ");
		if(strs.length>10)
		{
			String result="";
		  for(int i=0;i<10;i++)
		  {
			  result=result+strs[i]+" ";
		  }
		  return result+"...";
		}
		else
		{
			return description+"...";
		}
	}
	
		return "";
	}
	
	
	
}
