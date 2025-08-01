package com.lmao.demo.service;

import com.lmao.demo.enity.Product;
import com.lmao.demo.repo.CategoryRepo;
import com.lmao.demo.repo.ProductRepo;

import java.util.List;

public class ProductService {

    private ProductRepo productRepo = new ProductRepo();

    public List<Product> getProduct() {
        return productRepo.getProduct();
    }
}
