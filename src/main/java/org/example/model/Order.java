package org.example.model;

import org.example.enums.PaymentMethodEnum;
import org.example.service.DateConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private Long id;
    private int quantity;
    private Double total;
    private LocalDateTime date;
    private String sku;
    private String customerName;
    private String customerCpf;

    public Order(Long id, int quantity, Double total, LocalDateTime date, String sku, String customerName, String customerCpf) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.sku = sku;
        this.customerName = customerName;
        this.customerCpf = customerCpf;
    }

    public Order() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "\n" +
                "|----------------------------------------------------| \n" +
                "| SKU: " + sku + ",                                    \n" +
                "| Quantity: " + quantity + ",                          \n" +
                "| Total: R$" + total + ",                              \n" +
                "| Date: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ",\n" +
                "| Customer: " + customerName + ",                      \n" +
                "| Cpf: " + customerCpf + ",                            \n" +
                "| ---------------------------------------------------| \n";
    }
}
