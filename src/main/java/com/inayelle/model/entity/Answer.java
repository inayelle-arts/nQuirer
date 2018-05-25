package com.inayelle.model.entity;

public class Answer
{
	private int id;
	private int ownerId;
	private String content;
	private boolean isCorrect;
	private boolean isUserCorrect;
	
	public Answer(int id, int ownerId, String content, boolean isCorrect)
	{
		this.id = id;
		this.ownerId = ownerId;
		this.content = content;
		this.isCorrect = isCorrect;
		this.isUserCorrect = false;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public boolean getIsCorrect()
	{
		return isCorrect;
	}
	
	public void setCorrect(boolean correct)
	{
		isCorrect = correct;
	}
	
	public int getOwnerId()
	{
		return ownerId;
	}
	
	public void setOwnerId(int ownerId)
	{
		this.ownerId = ownerId;
	}
	
	public boolean getIsUserCorrect()
	{
		return isUserCorrect;
	}
	
	public void setUserCorrect(boolean userCorrect)
	{
		isUserCorrect = userCorrect;
	}
	
	@Override
	public String toString()
	{
		return "Answer{" +
				"id=" + id +
				", content='" + content + '\'' +
				", getIsCorrect=" + isCorrect +
				'}';
	}
}
