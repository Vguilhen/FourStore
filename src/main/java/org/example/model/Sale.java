package org.example.model;

import org.example.enums.PaymentMethodEnum;

import java.time.LocalDateTime;

public class Sale {

    private Long id;
    private int quantity;
    private Double total;
    private LocalDateTime date;
    private PaymentMethodEnum paymentMethod;
    private String sku;
    private String customerName;
    private String customerCpf;
    private String paymentData;

    public Sale(Long id, int quantity, Double total, LocalDateTime date, PaymentMethodEnum paymentMethod, String sku, String customerName, String customerCpf, String paymentData) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.sku = sku;
        this.customerName = customerName;
        this.customerCpf = customerCpf;
        this.paymentData = paymentData;
    }

    public Sale() {
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

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }

    @Override
    public String toString() {
        return  "\n" +
                "|----------------------------------------------------| \n" +
                "| SKU: " + sku + ",                                    \n" +
                "| Quantity: " + quantity + ",                          \n" +
                "| Total: R$" + total + ",                              \n" +
                "| Date: " + date + ",                                  \n" +
                "| Payment Method: " + paymentMethod + ",               \n" +
                "| Customer: " + customerName + ",                      \n" +
                "| Cpf: " + customerCpf + ",                            \n" +
                "| Dados do Pagamento: " + paymentData + ",             \n" +
                "| ---------------------------------------------------| \n";
    }
}
