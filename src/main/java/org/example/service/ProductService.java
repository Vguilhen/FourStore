package org.example.service;

import org.example.communication.MenuProductCommunication;
import org.example.dao.ProductDao;
import org.example.model.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductService {

    MenuProductCommunication menu = new MenuProductCommunication();
    OrderService orderService = new OrderService();
    ProductDao dao = new ProductDao();
    Scanner sc = new Scanner(System.in);
    String proceed;
    boolean condition = true;

    public void productServiceExecute() {
        ProductService productService = new ProductService();
        int menuNumber = 0;

        while (condition) {
            try {
                menu.showMenuProduct();
//                menuNumber = menu.menuOption();
                menuNumber = sc.nextInt();
                if (menuNumber >= 0 && menuNumber <= 6) {
                    switch (menuNumber) {
                        case 0:
                            condition = false;
                            break;
                        case 1:
                            productService.saveProduct();
                            continueLoop();
                            break;
                        case 2:
                            productService.listProductService();
                            continueLoop();
                            break;
                        case 3:
                            productService.findProductService();
                            continueLoop();
                            break;
                        case 4:
                            productService.updateProductService();
                            continueLoop();
                            break;
                        case 5:
                            orderService.orderServiceExecute();
                            condition = false;
                            break;
                        case 6:
                            productService.deleteProductService();
                            continueLoop();
                    }
                } else menu.infIvalidOption();
            } catch (InputMismatchException e) {
                menu.infIvalidOption();
                sc.nextLine();
            }
        }
    }

    public void saveProduct() {
        Product product = new Product();
        menu.infSku();
        boolean seq = true;
        while (seq) {
            String sku = menu.sku();
            Optional<Product> findBySku = dao.findBySku(sku);
            if (findBySku.isPresent()) {
                menu.infSkuAlreadExists();
            } else {
                product.setSku(sku);
                menu.infProductDescription();
                product.setDescription(menu.description());
                menu.infProductQuantity();
                product.setQuantity(menu.quantity());
                menu.infProductPrice();
                product.setPrice(menu.price());

                dao.save(product);
                menu.infSaveSucsses();
                seq = false;
            }
        }
    }

    public void listProductService() {

        List<Product> productList = dao.findAll();
        for (Product productInList : productList) {
            System.out.println(productInList.toString());
        }
    }

    public void findProductService() {

        menu.infSku();
        Optional<Product> findBySku = dao.findBySku(menu.sku());
        findBySku.ifPresent(productSku -> {
            System.out.println(productSku.toString());
        });
    }

    public void updateProductService() {

        menu.infSku();
        Optional<Product> updateOptional = dao.findBySku(menu.sku());
        updateOptional.ifPresent(update -> {
            System.out.println(update.toString());
        });
        Product update = updateOptional.get();
        menu.infNewSku();
        update.setSku(menu.sku());
        menu.infProductNewDescription();
        update.setDescription(menu.description());
        menu.infProductNewQuantity();
        update.setQuantity(menu.quantity());
        menu.infProductNewPrice();
        update.setPrice(menu.price());
        dao.update(update);
        menu.infUpdateSucsses();
    }

    public void continueLoop() {
        boolean localCond = true;
        while (localCond) {
            menu.infContinue();
            try {
                proceed = menu.yN();
                if (proceed.equals("n")) {
                    condition = false;
                    localCond = false;
                } else if (proceed.equals("s")) {
                    condition = true;
                    localCond = false;
                } else {
                    menu.infIvalidOption();
                    condition = false;
                }
            } catch (InputMismatchException e) {
                menu.infIvalidOption();
                sc.nextLine();
            }
        }
    }

    private void deleteProductService() {
        AtomicBoolean localCond = new AtomicBoolean(true);
        while (localCond.get()) {
            menu.infSku();
            String sku = menu.sku();
                Optional<Product> findBySku = dao.findBySku(sku);
                if (findBySku.isPresent()) {
                    dao.delete(sku);
                    menu.infProductDeleted();
                    localCond.set(false);
                } else {
                    menu.infSkuInvalid();
                    localCond.set(true);
                }
        }
    }
}