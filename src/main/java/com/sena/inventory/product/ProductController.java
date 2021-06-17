package com.sena.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    //localhost:8080/api/products/2
    @DeleteMapping(path="/{id}")
    @ResponseBody
    public Optional<Product> Delete(@PathVariable int id){
        Optional<Product> miProducto = productService.ObtenerPorId(id);
        productService.ValidateDelete(id);

            //mensaje = "Borrado con éxito";

        return miProducto;
    }
}
