package com.sena.inventory.Invoice;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Double subtotal;
    private Double total;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetail;

    public Invoice() {
    }

    public Invoice(LocalDate date, Double subtotal, Double total) {
        this.date = date;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Invoice(Integer id, LocalDate date, Double subtotal, Double total) {
        this.id = id;
        this.date = date;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<InvoiceDetail> getInvoiceDetail() {
        return invoiceDetail;
    }

    public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date=" + date +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", invoiceDetail=" + invoiceDetail +
                '}';
    }
}
