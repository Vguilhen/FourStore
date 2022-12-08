package org.example.service;

public class CardValidation {

    //(Luhn)
    public static int sumNumbers(int number) {
        if (number < 9) {
            return number;
        } else {
            return number % 10 + 1;
        }
    }

    public static boolean checkExpiration(String numeroCartao) {

        int sumEven = 0;
        int sumOdd = 0;
        int aux = 0;

        //PARES
        for (int j = numeroCartao.length() - 2; j >= 0; j = j - 2) {
            aux = Integer.parseInt(numeroCartao.charAt(j) + "");
            sumEven = sumEven + sumNumbers(aux * 2);
        }

        //IMPARES
        for (int i = numeroCartao.length() - 1; i >= 0; i = i - 2) {
            aux = Integer.parseInt(numeroCartao.charAt(i) + "");
            sumOdd = sumOdd + aux;
        }

        if ((sumEven + sumOdd) % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String checkCardBrand(String number1Id,
                                        String number2Id) {
        if (number2Id.equals("37")) {
            return "AMERICAN EXPRESS";
        } else if (number2Id.equals("35")) {
            return "JCB";
        } else if (number1Id.equals("4")) {
            return "VISA";
        } else if (number1Id.equals("5")) {
            return "MASTER";
        } else if (number1Id.equals("6")) {
            return "DISCOVER";
        } else {
            return "Bandeira desconhecida";
        }
    }
}

