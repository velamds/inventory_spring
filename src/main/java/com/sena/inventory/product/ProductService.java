package com.sena.inventory.product;

import com.sena.inventory.brand.Brand;
import com.sena.inventory.brand.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, BrandRepository brandRepository){
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
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

    public List<Product> findGreaterThan(Double price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> findByBrand(Integer brandId) {
        Optional<Brand> myBrand = brandRepository.findById(brandId);
        if(!myBrand.isPresent()){
            throw new IllegalStateException("La marca no existe");
        }
        return productRepository.findByBrand(myBrand.get());
    }

    // productRepository.deleteById(id);

    //productRepository.findById();
    //productRepository.save(product);

}
