package com.sena.inventory.product;

import com.sena.inventory.Invoice.Invoice;
import com.sena.inventory.Invoice.InvoiceDetail;
import com.sena.inventory.Invoice.InvoiceDetailRepository;
import com.sena.inventory.Invoice.InvoiceRepository;
import com.sena.inventory.brand.Brand;
import com.sena.inventory.brand.BrandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository, BrandRepository brandRepository, InvoiceRepository invoiceRepository, InvoiceDetailRepository invoiceDetailRepository){
        return args -> {
            Brand logitech = new Brand("Logitech");
            Brand genius = new Brand("Genius");
            brandRepository.saveAll(List.of(logitech, genius));
            Product teclado = new Product("Teclado",50000.00,20000.00,new Brand(1,""),true);
            Product mouse = new Product("Raton",20000.00,10000.00,new Brand(2,""),true);
            repository.saveAll(List.of(teclado,mouse));
            //Solo java 8
            ArrayList<Product> arrayListProductos = new ArrayList<>();
            arrayListProductos.add(teclado);
            arrayListProductos.add(mouse);
            repository.saveAll(arrayListProductos);

            Invoice factura = new Invoice(LocalDate.now(), 18.0, 20.0);
            InvoiceDetail detalle1 = new InvoiceDetail(teclado, 2, teclado.getPrice(),factura);
            InvoiceDetail detalle2 = new InvoiceDetail(mouse, 3, mouse.getPrice(),factura);

            invoiceRepository.save(factura);
            invoiceDetailRepository.saveAll(List.of(detalle1,detalle2));


        };
    };
}
