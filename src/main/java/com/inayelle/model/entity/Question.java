package com.inayelle.model.entity;

import java.util.HashSet;
import java.util.Set;

public class Question
{
	private int id;
	private int ownerId;
	private String content;
	private Set<Answer> answers;
	
	public Question(int id, int ownerId, String content)
	{
		this.id = id;
		this.ownerId = ownerId;
		this.content = content;
		this.answers = new HashSet<>();
	}
	
	public Question(int id, int ownerId, String content, Set<Answer> answers)
	{
		this.id = id;
		this.ownerId = ownerId;
		this.content = content;
		this.answers = answers;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
		for (var answer : answers)
			answer.setOwnerId(id);
	}
	
	public int getOwnerId()
	{
		return ownerId;
	}
	
	public void setOwnerId(int ownerId)
	{
		this.ownerId = ownerId;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public Set<Answer> getAnswers()
	{
		return answers;
	}
	
	public void setAnswers(Set<Answer> answers)
	{
		this.answers = answers;
	}
	
	public Answer getAnswerById(int id)
	{
		for (var answer : answers)
			if (answer.getId() == id)
				return answer;
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return "Question{" +
				"id=" + id +
				", ownerId=" + ownerId +
				", content='" + content + '\'' +
				", answers=" + answers +
				'}';
	}
}
