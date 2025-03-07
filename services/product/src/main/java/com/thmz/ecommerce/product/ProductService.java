package com.thmz.ecommerce.product;

import com.thmz.ecommerce.exceptions.ProductPurchaseException;
import com.thmz.ecommerce.product.dto.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public CreateResponse createProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);
        var productId = productRepository.save(product).getId();
        return CreateResponse.builder()
                .productId(productId)
                .build();
    }

    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    ) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {

            var product = storedProducts.get(i);

            var productRequest = sortedRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.getProductId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toproductPurchaseResponse(product, productRequest.getQuantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Product with id %s not found", productId)
                ));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}