package org.example.provider;

import org.example.service.CurrencyConverter;
import org.example.service.annotation.Currency;

@Currency("Euro")
public class Euro implements CurrencyConverter {
    @Override
    public double getCurrency(double amount) {
        return amount * 0.088;
    }
}
