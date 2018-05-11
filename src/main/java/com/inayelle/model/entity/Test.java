package com.inayelle.model.entity;

public class Test
{
	private int id;
	private int duration;
	private String name;
	private String description;
	private int totalQuestions;
	
	public Test(int id, int duration, String name, String description, int totalQuestions)
	{
		this.id = id;
		this.duration = duration;
		this.name = name;
		this.description = description;
		this.totalQuestions = totalQuestions;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getTotalQuestions()
	{
		return totalQuestions;
	}
	
	public void setTotalQuestions(int totalQuestions)
	{
		this.totalQuestions = totalQuestions;
	}
	
	@Override
	public String toString()
	{
		return "Test{" +
				"id=" + id +
				", duration=" + duration +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", totalQuestions=" + totalQuestions +
				'}';
	}
}
