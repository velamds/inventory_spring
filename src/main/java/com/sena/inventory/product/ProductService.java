package com.sena.inventory.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public List<Product> getProducts(){
        return List.of(
                new Product(
                        1,
                        "Teclado",
                        20000.00,
                        15000.00 ,
                        1
                )
        );
    }
}
