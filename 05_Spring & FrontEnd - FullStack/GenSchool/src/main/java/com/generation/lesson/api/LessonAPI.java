package com.generation.lesson.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lesson.model.entities.Lesson;
import com.generation.lesson.model.repository.LessonRepository;

@RestController
@RequestMapping("genschool/api/lessons")
public class LessonAPI
{
	@Autowired
	LessonRepository repo;	
	
	@PostMapping
	public ResponseEntity<Lesson> insert(@RequestBody Lesson lesson)
	{
		if(!lesson.isValid())
			return ResponseEntity.badRequest().body(lesson);
		lesson = repo.save(lesson);
		return ResponseEntity.ok(lesson);
	}	
}
