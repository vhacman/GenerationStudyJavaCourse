package com.generation.product.api.dto;

import com.generation.product.model.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDTOMapper
{
	public ProductDTO toDTO(Product product)
	{
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setErrors(product.getErrors());
		return dto;
	}

	public ProductDTO withoutErrorsDTO(Product product)
	{
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		return dto;
	}

	public Product toEntity(ProductDTO dto)
	{
		Product product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		return product;
	}

	public List<ProductDTO> toDTOList(List<Product> products)
	{
		List<ProductDTO> dtos = new ArrayList<>();
		for (Product product : products)
			dtos.add(withoutErrorsDTO(product));
		return dtos;
	}
}
