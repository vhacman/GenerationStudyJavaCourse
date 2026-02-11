package com.generation.product.api.dto;

import com.generation.product.model.entities.Product;
import com.generation.product.model.entities.Review;
import com.generation.product.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewDTOMapper
{
	@Autowired
	private ProductRepository productRepository;

	public ReviewDTO toDTO(Review review)
	{
		ReviewDTO dto = new ReviewDTO();
		dto.setId(review.getId());
		dto.setTitle(review.getTitle());
		dto.setAuthor(review.getAuthor());
		dto.setScore(review.getScore());
		dto.setProductId(review.getProduct() != null ? review.getProduct().getId() : null);
		dto.setErrors(review.getErrors());
		return dto;
	}

	public ReviewDTO withoutErrorsDTO(Review review)
	{
		ReviewDTO dto = new ReviewDTO();
		dto.setId(review.getId());
		dto.setTitle(review.getTitle());
		dto.setAuthor(review.getAuthor());
		dto.setScore(review.getScore());
		dto.setProductId(review.getProduct() != null ? review.getProduct().getId() : null);
		return dto;
	}

	public Review toEntity(ReviewDTO dto)
	{
		Review review = new Review();
		review.setTitle(dto.getTitle());
		review.setAuthor(dto.getAuthor());
		review.setScore(dto.getScore());
		Optional<Product> productOpt = productRepository.findById(dto.getProductId());
		if (productOpt.isEmpty())
			throw new RuntimeException("Prodotto non trovato con ID: " + dto.getProductId());
		review.setProduct(productOpt.get());
		return review;
	}

	public List<ReviewDTO> toDTOList(List<Review> reviews)
	{
		List<ReviewDTO> dtos = new ArrayList<>();
		for (Review review : reviews)
			dtos.add(withoutErrorsDTO(review));
		return dtos;
	}

}
