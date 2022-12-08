package org.example.communication;

import java.util.Scanner;

import static org.example.service.CardValidation.checkCardBrand;
import static org.example.service.CardValidation.checkExpiration;

public class CardCommunication {
    Scanner sc = new Scanner(System.in);
    String cardBrand = "";
    String cardNumber;
    boolean condition = true;

    public String cardCommunication() {

        while (condition) {
            System.out.println("Informe o número do cartão");
            String numberCard = sc.next();
            cardNumber = numberCard;

            if (numberCard.length() >= 13 && numberCard.length() <= 16) {

                boolean flag = checkExpiration(numberCard);

                if (flag) {
                    cardBrand = checkCardBrand(numberCard.substring(0, 1),
                            numberCard.substring(0, 2));
                    System.out.println("Cartão " + cardBrand + " Válido, Número:" + numberCard + "\n");
                    condition = false;
                } else {
                    System.out.println("Cartão Inválido");
                }
            } else {
                System.out.println("Número de cartão inválido");
            }
        } return cardNumber;
    }
}