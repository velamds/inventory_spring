package com.sena.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
       //Here you should write your bussines logic

       return productRepository.findByStateTrue();

    }

    public Product ValidateDelete(int id) {
        Product product = productRepository.getById(id);
        if(product == null){
            throw new IllegalStateException("El producto con el id="+id+" no existe hermano(a).");
        }
        product.setState(false);

        return productRepository.save(product);
    }

    public Optional<Product> ObtenerPorId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()){
            throw new IllegalStateException("El producto no existe");
        }
        return optionalProduct;
    }

    public Product CreateProduct(Product product) {
        //Tomar las unidades actuales del producto, sumar las que ingresan y actualizar dicha cantidad
        return productRepository.save(product);
    }

    // productRepository.deleteById(id);

    //productRepository.findById();
    //productRepository.save(product);

}
