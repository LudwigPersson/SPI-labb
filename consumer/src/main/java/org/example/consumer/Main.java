package org.example.consumer;


import org.example.service.CurrencyConverter;
import org.example.service.annotation.Currency;

import java.util.Scanner;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {

        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);

        Scanner scanner = new Scanner(System.in);


        while (true) {
            displayMenu(loader);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    performConversion("Euro", loader, scanner);
                    break;
                case 2:
                    performConversion("Dkk", loader, scanner);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void displayMenu(ServiceLoader<CurrencyConverter> loader) {
        System.out.println("Choose an option:");
        int option = 1;
        for (CurrencyConverter converter : loader) {
            Currency currencyAnnotation = converter.getClass().getAnnotation(Currency.class);
            if (currencyAnnotation != null) {
                System.out.println(option + ". Convert from SEK to " + currencyAnnotation.value());
                option++;
            }
        }
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void performConversion(String targetCurrency, ServiceLoader<CurrencyConverter> loader, Scanner scanner) {
        System.out.print("Enter amount in SEK: ");
        double amountSEK = scanner.nextDouble();

        for (CurrencyConverter converter : loader) {
            Currency currencyAnnotation = converter.getClass().getAnnotation(Currency.class);
            if (currencyAnnotation != null && targetCurrency.equals(currencyAnnotation.value())) {
                double result = converter.getCurrency(amountSEK);
                System.out.println("Converted amount from SEK to " + currencyAnnotation.value() + ": " + result);
                return;
            }
        }
        System.out.println("Currency converter for " + targetCurrency + " not found.");
    }
}

