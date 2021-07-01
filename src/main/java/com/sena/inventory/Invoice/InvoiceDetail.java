package com.sena.inventory.Invoice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sena.inventory.product.Product;

import javax.persistence.*;

@Entity
@Table
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    private Integer quantity;
    private Double price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "invoice")
    private Invoice invoice;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Integer id, Product product, Integer quantity, Double price, Invoice invoice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.invoice = invoice;
    }

    public InvoiceDetail(Product product, Integer quantity, Double price, Invoice invoice) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.invoice = invoice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "InvoiceDetail{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", invoice=" + invoice +
                '}';
    }
}
