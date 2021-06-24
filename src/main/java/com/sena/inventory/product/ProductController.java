package com.sena.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
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

    @PostMapping
    public Product CreateProduct(@Valid @RequestBody Product product){
        return this.productService.CreateProduct(product);
    }

    @PutMapping
    public Product ValidateAndUpdate(@Valid @RequestBody Product product){
        if(product.getId() == null){
            throw new IllegalStateException("falta el id!");
        }
        return productService.CreateProduct(product);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> ManejoExcepcionValidacion(MethodArgumentNotValidException exception){
        Map<String,String> errores = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });
        return errores;
    }

    //localhost:8080/api/products/2
    @DeleteMapping(path="/{id}")
    @ResponseBody
    public Product Delete(@PathVariable int id){
        return productService.ValidateDelete(id);
    }
}
