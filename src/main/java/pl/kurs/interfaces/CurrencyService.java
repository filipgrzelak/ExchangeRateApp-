package pl.kurs.interfaces;

import pl.kurs.exceptions.InvalidDataInputException;

import java.io.IOException;
import java.math.BigDecimal;

public interface CurrencyService {

    BigDecimal exchangeCurrencies(String currencyFrom, String currencyTo, double amount) throws InvalidDataInputException, IOException;

}
