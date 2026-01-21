package com.generation.m11.lesson;

public class EnglishTeacherView implements TeacherView
{
	@Override
	public String render(Teacher teacher)
	{
		
		return "Prof. " + teacher.getName() + " " + teacher.getSurname() + ", teaches " + teacher.getSubject();
	}
	
}
