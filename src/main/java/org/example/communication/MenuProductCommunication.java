package org.example.communication;

import org.example.service.DateConfig;

import java.util.Scanner;

public class MenuProductCommunication {

    DateConfig date = new DateConfig();
    String sku;
    String description;
    int quantity;
    int menuOption;
    Double price;
    String yN;


    Scanner sc = new Scanner(System.in);
    String menuProduct =
            """

            |----------------------------------------------------|\s
            |                    FourStore                       |\s
            |----------------------------------------------------|\s
            | Digite 1 para cadastrar um novo produto no estoque |\s
            | Digite 2 para mostrar os produtos no estoque       |\s
            | Digite 3 para consultar um produto pelo SKU        |\s
            | Digite 4 para alterar um produto no estoque        |\s
            | Digite 5 para venda                                |\s
            | Digite 6 para deletar um produto no estoque        |\s
            | Digite 0 para sair                                 |\s
            | -------------------------------------------------- |\s""";

    public void showMenuProduct() {
        System.out.println(menuProduct);
    }
    public void infSku() {
        System.out.println("Informe o SKU do produto: ");
    }
    public void infNewSku() {
        System.out.println("Informe o Novo SKU do produto: ");
    }
    public void infSkuAlreadExists() {
        System.out.println("sku já exite, por favor informe um novo sku");
    }

    public void infProductDescription() {
        System.out.println("Informe a Descrição do produto: ");
    }
    public void infProductNewDescription() {
        System.out.println("Informe a Nova descrição do produto: ");
    }

    public void infProductQuantity() {
        System.out.println("Informe a Quantidade do produto: ");
    }
    public void infProductNewQuantity() {
        System.out.println("Informe a Nova quantidade do produto: ");
    }

    public void infProductPrice() {
        System.out.println("Informe o Valor do produto: ");
    }
    public void infProductNewPrice() {
        System.out.println("Informe o Novo valor do produto: ");
    }

    public void infSaveSucsses() {
        System.out.println("Produto cadastrado com sucesso! ");
    }

    public void infUpdateSucsses() {
        System.out.println("Produto atualizado com sucesso! ");
    }

    public void infContinue() {
        System.out.println("\nDeseja voltar para o menu? s/n ");
    }

    public void infIvalidOption() {
        System.out.println("Opção inválida, digite uma opção oferecida no menu");
    }
    public void infSkuInvalid() {
        System.out.println("sku inexistetnte ou incorreto, favor digite um sku válido");
    }
    public void infProductDeleted() {
        System.out.println("O produto foi removido do estoque com sucesso!");
    }
    public void exitProgram() {
        System.out.println("Programa finalizado: " + date.formatDateTime);
    }

    public String yN() {
        yN = sc.next();
        return yN;
    }


    public String sku() {
        sku = sc.next();
        return sku;
    }
    public int menuOption() {
        menuOption = sc.nextInt();
        return menuOption;
    }

    public String description() {
        description = sc.next();
        return description;
    }

    public int quantity() {
        quantity = sc.nextInt();
        return quantity;
    }

    public Double price() {
        price = sc.nextDouble();
        return price;
    }


}


