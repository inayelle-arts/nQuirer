package com.inayelle.model.entity;

public class Question
{
	private int id;
	private String content;
	private String explanation;
	private int answerHash;
	private int testId;
	
	public Question(int id, String content, String explanation, int answerHash, int testId)
	{
		this.id = id;
		this.content = content;
		this.explanation = explanation;
		this.answerHash = answerHash;
		this.testId = testId;
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
	
	public String getExplanation()
	{
		return explanation;
	}
	
	public void setExplanation(String explanation)
	{
		this.explanation = explanation;
	}
	
	public int getAnswerHash()
	{
		return answerHash;
	}
	
	public void setAnswerHash(int answerHash)
	{
		this.answerHash = answerHash;
	}
	
	public int getTestId()
	{
		return testId;
	}
	
	public void setTestId(int testId)
	{
		this.testId = testId;
	}
	
	@Override
	public String toString()
	{
		return "Question{" +
				"id=" + id +
				", content='" + content + '\'' +
				", explanation='" + explanation + '\'' +
				", answerHash=" + answerHash +
				", testId=" + testId +
				'}';
	}
}
