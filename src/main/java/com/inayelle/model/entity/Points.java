package com.inayelle.model.entity;

public class Points
{
	private int userPoints;
	private int maxPoints;
	
	public Points(int userPoints, int maxPoints)
	{
		this.userPoints = userPoints;
		this.maxPoints = maxPoints;
	}
	
	public int getUserPoints()
	{
		return userPoints;
	}
	
	public int getMaxPoints()
	{
		return maxPoints;
	}
}
