package com.generation.product.api;

import com.generation.product.api.dto.ProductDTO;
import com.generation.product.api.dto.ProductDTOMapper;
import com.generation.product.api.dto.ReviewDTO;
import com.generation.product.api.dto.ReviewDTOMapper;
import com.generation.product.model.entities.Product;
import com.generation.product.model.entities.Review;
import com.generation.product.model.repository.ProductRepository;
import com.generation.product.model.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product/api/products")
public class ProductAPI
{
	@Autowired
	private ProductRepository	productRepository;
	@Autowired
	private ReviewRepository	reviewRepository;
	@Autowired
	private ProductDTOMapper		productDTOMapper;
	@Autowired
	private ReviewDTOMapper		reviewDTOMapper;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts()
	{
		return ResponseEntity.ok(productDTOMapper.toDTOList(productRepository.findAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") int id)
	{
		Optional<Product> result = productRepository.findById(id);
		if (result.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDTOMapper.withoutErrorsDTO(result.get()));
	}

	@PostMapping
	public ResponseEntity<Object> createProduct(@RequestBody Product product)
	{
		if (!product.getErrors().isEmpty())
			return ResponseEntity.badRequest().body(productDTOMapper.toDTO(product));
		if (productRepository.existsByName(product.getName()))
			return ResponseEntity.badRequest().body("Esiste già un prodotto con questo nome");
		Product saved = productRepository.save(product);
		return ResponseEntity.ok(productDTOMapper.withoutErrorsDTO(saved));
	}

	@GetMapping("/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> getProductReviews(@PathVariable("id") int id)
	{
		Optional<Product> result = productRepository.findById(id);
		if (result.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reviewDTOMapper.toDTOList(result.get().getReviews()));
	}

	@PostMapping("/{id}/reviews")
	public ResponseEntity<Object> createReview(@PathVariable("id") int id, @RequestBody Review review)
	{
		Optional<Product> result = productRepository.findById(id);
		if (result.isEmpty())
			return ResponseEntity.notFound().build();
		review.setProduct(result.get());
		if (!review.getErrors().isEmpty())
			return ResponseEntity.badRequest().body(reviewDTOMapper.toDTO(review));
		Review saved = reviewRepository.save(review);
		return ResponseEntity.ok(reviewDTOMapper.withoutErrorsDTO(saved));
	}

}
