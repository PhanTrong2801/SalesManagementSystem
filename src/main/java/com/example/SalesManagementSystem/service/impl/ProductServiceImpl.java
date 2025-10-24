package com.example.SalesManagementSystem.service.impl;

import com.example.SalesManagementSystem.dto.ProductDTO;
import com.example.SalesManagementSystem.entity.Category;
import com.example.SalesManagementSystem.entity.Product;
import com.example.SalesManagementSystem.entity.Supplier;
import com.example.SalesManagementSystem.repository.CategoryRepository;
import com.example.SalesManagementSystem.repository.ProductRepository;
import com.example.SalesManagementSystem.repository.SupplierRepository;
import com.example.SalesManagementSystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product();
        mapToEntity(dto, product);
        product = productRepository.save(product);
        return mapToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Product not found"));
        mapToEntity(dto,product);
        return mapToDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        product.setIsDeleted(true);
        productRepository.save(product);

    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product  = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        return mapToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .filter(p -> !p.getIsDeleted())
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        return productRepository.findAll().stream()
                .filter(p-> !p.getIsDeleted() &&
                        (p.getName().toLowerCase().contains(keyword.toLowerCase())||
                                p.getSku().toLowerCase().contains(keyword.toLowerCase())))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private void mapToEntity(ProductDTO dto, Product product){
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCostPrice(dto.getCostPrice());
        product.setSalePrice(dto.getSalePrice());
        product.setUnit(dto.getUnit());
        product.setIsActive(true);
        if (dto.getCategoryId() != null){
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(()-> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        if (dto.getSupplierId() != null){
            Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(()-> new RuntimeException("Supplier not found"));
            product.setSupplier(supplier);
        }
    }

    private ProductDTO mapToDTO(Product product){
        return ProductDTO.builder()
                .productId(product.getProductId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .costPrice(product.getCostPrice())
                .salePrice(product.getSalePrice())
                .unit(product.getUnit())
                .categoryId(product.getCategory() != null ? product.getCategory().getCategoryId() : null)
                .supplierId(product.getSupplier() != null ? product.getSupplier().getSupplierId() : null)
                .build();

    }
}
