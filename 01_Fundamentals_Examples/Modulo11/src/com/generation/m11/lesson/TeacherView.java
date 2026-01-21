package com.generation.m11.lesson;

import java.util.List;

public interface TeacherView
{
	String		render(Teacher teacher);
	
	default String	render(List<Teacher> teachers)
	{
	    String res = "";
	    for(Teacher teacher : teachers)
	        res += render(teacher) + "\n";
	    return res;
	}

}