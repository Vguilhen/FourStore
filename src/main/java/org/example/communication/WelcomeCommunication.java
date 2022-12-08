package org.example.communication;

import org.example.service.DateConfig;

public class WelcomeCommunication {

    DateConfig date = new DateConfig();

    public void showWelcome() {
        System.out.println("\n Bem vindo: " + date.formatDateTime);
    }
}
