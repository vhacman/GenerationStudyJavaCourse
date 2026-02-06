package com.generation.lesson.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lesson.model.entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>
{
	
}
