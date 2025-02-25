package org.example.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() { return productRepository.findAll(); }

    public Product createProduct(ProductDto dto) {
        return productRepository.save(Product.builder().name(dto.getName()).price(dto.getPrice()).categoryId(dto.getCategoryId()).build());
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
