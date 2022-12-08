package org.example.communication;

import java.util.Scanner;

public class MenuOrderCommunication {

    Scanner sc = new Scanner(System.in);

    int menuOption;
    int requestedQuantity;
    String customerName;
    String cpf;
    String sku;
    String pixKey;
    String yN;

    String menuOrder =
            """

                    |----------------------------------------------------|\s
                    |                   Menu Vendas                      |\s
                    |----------------------------------------------------|\s
                    | Digite 1 para adicionar um produto no carrinho     |\s
                    | Digite 2 para mostrar os produtos no carrinho      |\s
                    | Digite 3 finalizar a venda                         |\s
                    | Digite 4 consultar vendas realizadas               |\s
                    | Digite 0 para voltar ao menu anterior              |\s
                    | -------------------------------------------------- |\s""";
    public void showMenuOrder() {
        System.out.println(menuOrder);
    }
    public void infIvalidMenuOption() {
        System.out.println("Opção inválida, digite uma opção oferecida no menu");
    }
    public void infCustomerName() {
        System.out.println("Informe o Nome do Cliente");
    }
    public void infOrderSaved() {
        System.out.println("Produto Salvo no carrinho!");
    }
    public void infTryAgain() {
        System.out.println("Por Favor tente novamente");
    }
    public void infCpf() {
        System.out.println("Informe o CPF do Cliente (Somente números sem espaço!) ");
    }
    public void infWouldCpf() {
        System.out.println("Gostaria de informar o cpf? s/n ");
    }
    public void infSku() {
        System.out.println("Informe o SKU do produto: ");
    }
    public void infInvalidCpf() {
        System.out.println("CPF inválido!");
    }
    public void infSkuInvalid() {
        System.out.println("sku inexistetnte, favor digite um sku válido");
    }
    public void infQuantity() {
        System.out.println("Informe a quantidade:");
    }
    public void infAreYouTrying() {
        System.out.print("Você está tentando adicionar ");
    }
    public void infYouHave(){
        System.out.print(" no carrinho e há apenas ");
    }
    public void infInvalidOption() {
        System.out.println("Valor inválido, digite um valor inteiro válido!");
    }
    public void infIvalidYesOrNo() {
        System.out.println("Opção inválida, digite s para sim e n para não");
    }
    public void infInStock() {
        System.out.println(" no estoque, por favor, ");
    }
    public void infSuccessfulSale() {
        System.out.println("Venda realizada com sucesso! ");
    }
    public void infPixKey() {
        System.out.println("informe o número da chave Pix");
    }
    public void infEmptyCart() {
        System.out.println("Seu Carrinho está vazio!\n");
    }
    public void infTryNewOrder() {
        System.out.println("Insira um produto no carrinho antes de continuar!\n");
    }
    public void infCartTitle() {
        System.out.println("\n| Carrinho de compras:\n");
    }
    public void infSaleTitle() {
        System.out.println("\n| Vendas Realizadas:\n");
    }
    public void infLineEnd() {
        System.out.println(" Fim do Carrinho!\n\n");
    }
    public void infSaleEnd() {
        System.out.println("| Fim da lista de vendas!\n");
    }
    public void infEmptyList() {
        System.out.println("| -------------------------------------------------- |\n" +
                "| A Lista de Vendas está vazia\n" +
                "| -------------------------------------------------- |\n");
    }
    public void returnInt(int i){
        System.out.print(i);
    }
    public String infCash() {
        return "Cash";
    }
    public int requestedQuantity() {
        requestedQuantity = sc.nextInt();
        return requestedQuantity;
    }
    public int menuOption() {
        menuOption = sc.nextInt();
        return menuOption;
    }
    public String  customerName() {
        customerName = sc.next();
        return customerName;
    }
    public String cpf() {
        cpf = sc.next();
        return cpf;
    }
    public String sku() {
        sku = sc.next();
        return sku;
    }
    public String  pixKey() {
        pixKey = sc.next();
        return pixKey;
    }
    public String yN() {
        yN = sc.next();
        return yN;
    }
}