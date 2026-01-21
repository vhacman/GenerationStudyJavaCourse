package com.generation.m11.lesson;

public class ItalianTeacherView implements TeacherView
{
	@Override
	public String render(Teacher teacher)
	{
		
		return "Prof. " + teacher.getName() + " " + teacher.getSurname() + ", insegna " + teacher.getSubject();
	}
	
}
