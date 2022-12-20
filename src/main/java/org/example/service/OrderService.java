package org.example.service;

import org.example.communication.CardCommunication;
import org.example.communication.MenuOrderCommunication;
import org.example.communication.MenuPaymentCommunication;
import org.example.dao.OrderDao;
import org.example.dao.ProductDao;
import org.example.dao.SaleDao;
import org.example.enums.PaymentMethodEnum;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Sale;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OrderService {
    MenuOrderCommunication menuOrder = new MenuOrderCommunication();
    MenuPaymentCommunication menuPayment = new MenuPaymentCommunication();
    CardCommunication cardCommunication = new CardCommunication();
    ProductDao productDao = new ProductDao();
    OrderDao orderDao = new OrderDao();
    Order order = new Order();
    SaleDao saleDao = new SaleDao();
    Scanner sc = new Scanner(System.in);
    String skuValid;
    int quantityValid;
    int currentQuantityInStock;
    Double totalValid;
    String cpfValid;
    boolean condition = true;


    public void orderServiceExecute() {
        OrderService orderService = new OrderService();
        int menuNumber = 0;

        while (condition) {
            try {
                menuOrder.showMenuOrder();
                menuNumber = sc.nextInt();
                if (menuNumber >= 0 && menuNumber <= 5) {
                    switch (menuNumber) {
                        case 0:
                            condition = false;
                            ProductService productService = new ProductService();
                            productService.productServiceExecute();
                            break;
                        case 1:
                            orderService.addOrder();
                            break;
                        case 2:
                            orderService.listOrderService();
                            break;
                        case 3:
                            updateProductOrderAndSale();
                            break;
                        case 4:
                            List<Sale> saleList = saleDao.findAll();
                            menuOrder.infSaleTitle();
                            if (saleList.isEmpty()) {
                                menuOrder.infEmptyList();
                            } else {
                                for (Sale saleInList : saleList) {
                                    System.out.println(saleInList.toString());
                                }
                            }
                            menuOrder.infSaleEnd();
                            break;
                        case 5:
                            menuOrder.showMenuOrder();
                            condition = false;
                            break;
                    }
                } else menuOrder.infIvalidMenuOption();
            } catch (InputMismatchException e) {
                menuOrder.infIvalidMenuOption();
                sc.nextLine();
            }
        }
    }

    private void listOrderService() {
        List<Order> productList = orderDao.findAll();
        if (productList.isEmpty()) {
            menuOrder.infEmptyCart();
        } else {
            menuOrder.infCartTitle();
            for (Order orderInList : productList) {
                System.out.println(orderInList.toString());
            }
            menuOrder.infLineEnd();
        }
    }

    private void addOrder() {
        boolean localCond = true;
        while (localCond) {
            if (skuValidation() && quantityValidation() && cpfValidation()) {
                order.setSku(skuValid);
                order.setQuantity(quantityValid);
                order.setTotal(totalValid);
                menuOrder.infCustomerName();
                order.setCustomerName(menuOrder.customerName());
                order.setCustomerCpf(cpfValid);
                orderDao.save(order);
                menuOrder.infOrderSaved();
                localCond = false;
            } else {
                menuOrder.infTryAgain();
            }
        }
    }

    private boolean cpfValidation() {
        boolean seq = true;
        while (seq) {
                menuOrder.infWouldCpf();
                String yN = sc.nextLine();
                if (yN.equals("s")) {
                    menuOrder.infCpf();
                    String customerCpf = menuOrder.cpf();
                    if (CpfValidation.isCPF(customerCpf)) {
                        cpfValid = customerCpf;
                        seq = false;
                    } else {
                        menuOrder.infInvalidCpf();
                    }
                }
                if (yN.equals("n")) {
                    cpfValid = "00000000000";
                    seq = false;
                }
        }
        return true;
    }

    public boolean skuValidation() {
        boolean seq = true;
        while (seq) {
            menuOrder.infSku();
            String sku = menuOrder.sku();
            Optional<Product> findBySku = productDao.findBySku(sku);
            if (findBySku.isPresent()) {
                skuValid = sku;
                seq = false;
            } else {
                menuOrder.infSkuInvalid();
            }
        }
        return true;
    }

    public boolean quantityValidation() {
        Optional<Product> findBySku = productDao.findBySku(skuValid);
        findBySku.ifPresent(product -> {
            boolean localVar = true;
            while (localVar) {
                int quantityInStock = product.getQuantity();
                menuOrder.infQuantity();
                try {
                    int requestedQuantity = menuOrder.requestedQuantity();
                    if (quantityInStock < requestedQuantity || requestedQuantity < 0) {
                        menuOrder.infAreYouTrying();
                        menuOrder.returnInt(requestedQuantity);
                        menuOrder.infYouHave();
                        menuOrder.returnInt(quantityInStock);
                        menuOrder.infInStock();
                    } else {
                        int currentQuantity = quantityInStock - requestedQuantity;
                        quantityValid = requestedQuantity;
                        currentQuantityInStock = currentQuantity;
                        totalValid = quantityValid * product.getPrice();
                        localVar = false;
                    }
                } catch (Exception ex) {
                    menuOrder.infInvalidOption();
                    sc.nextLine();
                }
            }
        });
        return true;
    }

    public void updateProductOrderAndSale() {
        Sale sale = new Sale();
        boolean conditionPaymentMethod = true;
        while (conditionPaymentMethod) {
            List<Order> ordertList = orderDao.findAll();
            if (ordertList.isEmpty()) {
                menuOrder.infEmptyCart();
                menuOrder.infTryNewOrder();
                conditionPaymentMethod = false;
            } else {
                for (Order orderInList : ordertList) {
                    sale.setQuantity(orderInList.getQuantity());
                    sale.setTotal(orderInList.getTotal());
                    sale.setSku(orderInList.getSku());
                    sale.setCustomerName(orderInList.getCustomerName());
                    sale.setCustomerCpf(orderInList.getCustomerCpf());
                    sale.setPaymentMethod(PaymentMethodEnum.CASH);

                    Optional<Product> productOptional = productDao.findBySku(orderInList.getSku());

                    Product product = productOptional.get();

                    currentQuantityInStock = product.getQuantity() - orderInList.getQuantity();

                    product.setQuantity(currentQuantityInStock);
                    productDao.updateQuantity(product);

                    orderDao.delete(orderInList.getSku());
                    saleDao.save(sale);
                }
                menuPayment.showMenuPaymentMethod();
                int paymentMethod = menuOrder.menuOption();
                if (paymentMethod > 0 && paymentMethod < 5) {
                    switch (paymentMethod) {
                        case 1:
                            for (Order orderInList : ordertList) {
                                Optional<Sale> saleOptional = saleDao.findBySku(orderInList.getSku());
                                Sale sales = saleOptional.get();
                                sales.setPaymentMethod(PaymentMethodEnum.DEBITCARD);
                                sales.setPaymentData(cardCommunication.cardCommunication());
                                saleDao.update(sales);
                            }
                            conditionPaymentMethod = false;
                            menuOrder.infSuccessfulSale();
                            break;
                        case 2:
                            for (Order orderInList : ordertList) {
                                Optional<Sale> saleOptional = saleDao.findBySku(orderInList.getSku());
                                Sale sales = saleOptional.get();
                                sales.setPaymentMethod(PaymentMethodEnum.CREDITCARD);
                                sales.setPaymentData(cardCommunication.cardCommunication());
                                saleDao.update(sales);
                            }
                            conditionPaymentMethod = false;
                            menuOrder.infSuccessfulSale();
                            break;
                        case 3:
                            menuOrder.infPixKey();
                            String chavePix = menuOrder.pixKey();
                            for (Order orderInList : ordertList) {
                                Optional<Sale> saleOptional = saleDao.findBySku(orderInList.getSku());
                                Sale sales = saleOptional.get();
                                sales.setPaymentMethod(PaymentMethodEnum.PIX);
                                sales.setPaymentData(chavePix);
                                saleDao.update(sales);
                            }
                            conditionPaymentMethod = false;
                            menuOrder.infSuccessfulSale();
                            break;
                        case 4:
                            for (Order orderInList : ordertList) {
                                Optional<Sale> saleOptional = saleDao.findBySku(orderInList.getSku());
                                Sale sales = saleOptional.get();
                                sales.setPaymentMethod(PaymentMethodEnum.CASH);
                                sales.setPaymentData(menuOrder.infCash());
                                saleDao.update(sales);
                            }
                            conditionPaymentMethod = false;
                            menuOrder.infSuccessfulSale();
                    }
                } else {
                    menuOrder.infIvalidMenuOption();
                }
                for (Order orderInList : ordertList) {
                    orderDao.delete(orderInList.getSku());
                }
            }
        }
    }
}
