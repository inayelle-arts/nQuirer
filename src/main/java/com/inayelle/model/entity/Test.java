package com.inayelle.model.entity;

import java.util.Set;

public class Test
{
	private int id;
	private String title;
	private String description;
	private Set<Question> questions;
	
	public Test(int id, String title, String description)
	{
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	public Test(int id, String title, String description, Set<Question> questions)
	{
		this.id = id;
		this.title = title;
		this.description = description;
		this.questions = questions;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
		for (var question : questions)
			question.setOwnerId(id);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Set<Question> getQuestions()
	{
		return questions;
	}
	
	public void setQuestions(Set<Question> questions)
	{
		this.questions = questions;
	}
	
	public Question getQuestionById(int id)
	{
		for (var question : questions)
			if (question.getId() == id)
				return question;
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return "Test{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", questions=" + questions +
				'}';
	}
}
