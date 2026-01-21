package com.generation.m11.lesson;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Console;

public class Lesson019 
{
    public static void main(String[] args) 
    {
        List<Teacher> people = new ArrayList<Teacher>();
        people.add(new Teacher("George", "Romano", "1960-01-01", "M", "Cinema", 1200));
        people.add(new Teacher("George", "Romano", "1960-01-01", "M", "Music", 1000));
        
        String language = Console.readString();
        
        TeacherView view = language.equals("IT") ? new ItalianTeacherView() : new EnglishTeacherView();
        
        Console.print(view.render(people));
    }
}
