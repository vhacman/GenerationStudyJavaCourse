package com.generation.product.api;

import com.generation.product.api.dto.ReviewDTO;
import com.generation.product.api.dto.ReviewDTOMapper;
import com.generation.product.model.entities.Review;
import com.generation.product.model.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review/api/reviews")
public class ReviewAPI
{
	@Autowired
	private ReviewRepository	reviewRepository;
	@Autowired
	private ReviewDTOMapper		reviewDTOMapper;

	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getAllReviews()
	{
		return ResponseEntity.ok(reviewDTOMapper.toDTOList(reviewRepository.findAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReviewDTO> getReviewById(@PathVariable("id") int id)
	{
		Optional<Review> result = reviewRepository.findById(id);
		if (result.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reviewDTOMapper.withoutErrorsDTO(result.get()));
	}
}
