package org.example.communication;

public class MenuPaymentCommunication {

    String menuPaymentMethod =
            """
            
            |----------------------------------------------------|\s
            | Informe o método de pagamento:                     |\s
            |----------------------------------------------------|\s
            | Digite 1 para pagar com cartão de débito           |\s
            | Digite 2 para pagar com cartão de crédito          |\s
            | Digite 3 para pagar com PIX                        |\s
            | Digite 4 para pagar em dinheiro                    |\s
            | -------------------------------------------------- |\s""";
    public void showMenuPaymentMethod(){
        System.out.println(menuPaymentMethod);
    }
}


