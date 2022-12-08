package org.example.communication;

import org.example.service.ProductService;

public class Main {
    public static void main(String[] args) {

        ProductService productService = new ProductService();
        WelcomeCommunication welcomeCommunication = new WelcomeCommunication();
        MenuProductCommunication menu = new MenuProductCommunication();

        welcomeCommunication.showWelcome();
        productService.productServiceExecute();
        menu.exitProgram();
    }
}
