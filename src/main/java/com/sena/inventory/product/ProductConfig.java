package com.sena.inventory.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository){
        return args -> {
            Product teclado = new Product("Teclado",50000.00,20000.00,1,true);
            Product mouse = new Product("Raton",20000.00,10000.00,1,true);
            repository.saveAll(List.of(teclado,mouse));
            //Solo java 8
            ArrayList<Product> arrayListProductos = new ArrayList<>();
            arrayListProductos.add(teclado);
            arrayListProductos.add(mouse);
            repository.saveAll(arrayListProductos);

        };
    };
}
