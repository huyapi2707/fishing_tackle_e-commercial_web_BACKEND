package com.huydang.fishingsalebackend.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private int limit = 60;
    public ProductDTO getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return mapper.apply(optionalProduct.get());
        }
        return null;
    }

    public List<ProductDTO> getProductByPage(int page){
        Pageable pageNumber = PageRequest.of(page, limit);
        List<Product> products =  productRepository.findAll(pageNumber).stream().toList();
        List<ProductDTO> result = products.stream().map(product -> mapper.apply(product)).collect(Collectors.toList());
        return result;
    }

    public List<ProductDTO> getProductBySubtypeId(int subtypeId, int page) {
        Pageable pageNumber = PageRequest.of(page, limit);
        List<Product> products = productRepository.findAllBySubtypeId(subtypeId, pageNumber).stream().toList();
        List<ProductDTO> result = products.stream().map(product -> mapper.apply(product)).collect(Collectors.toList());
        return result;
    }

    public List<ProductDTO> getProductBySubtypeId(int id, int page, int withLimit) {
        Pageable pageNumber = PageRequest.of(page, withLimit);
        List<Product> products = productRepository.findAllBySubtypeId(id, pageNumber).stream().toList();
        List<ProductDTO> result = products.stream().map(product -> mapper.apply(product)).collect(Collectors.toList());
        return result;
    }

    public List<ProductDTO> getProductByCategoryId(int categoryId, int page) {
        Pageable pageNumber = PageRequest.of(page, limit);
        List<Product> products = productRepository.findAllByCategoryId(categoryId, pageNumber).stream().toList();
        List<ProductDTO> result = products.stream().map(product -> mapper.apply(product)).collect(Collectors.toList());
        return result;
    }

    public List<ProductDTO> getProductByCategoryId(int id, int page, int withLimit) {
        Pageable pageNumber = PageRequest.of(page, withLimit);
        List<Product> products = productRepository.findAllByCategoryId(id, pageNumber);
        List<ProductDTO> result = products.stream().map(product -> mapper.apply(product)).collect(Collectors.toList());
        return result;
    }


    public List<ProductDTO> getProductByBrand(int brandId, int page) {
        Pageable pageNumber = PageRequest.of(page, limit);
        List<Product> products = productRepository.findAllByBrandId(brandId, pageNumber).stream().toList();
        List<ProductDTO> result = products.stream().map(product -> mapper.apply(product)).collect(Collectors.toList());
        return result;
    }

    public int countPageByCategoryId(int id) {
        return (int)(productRepository.countAllByCategoryId(id)/limit) + 1;
    }

    public int countPageBySubtypeId(int id) {
        return (int)(productRepository.countAllBySubtypeId(id)/limit) + 1;
    }

    public int countPageByBrandId(int id) {
        return (int)(productRepository.countAllByBrandId(id)/limit) + 1;
    }

    public int countPageTotal() {
        return (int)(productRepository.countAll()/limit + 1);
    }
}
