package pl.kurs.services;

import pl.kurs.exceptions.InvalidDataInputException;
import pl.kurs.interfaces.CurrencyService;
import pl.kurs.interfaces.GetRateFromUrlService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CurrencyServiceImpl implements CurrencyService {

    private GetRateFromUrlService getRateFromUrlService;



    public CurrencyServiceImpl(GetRateFromUrlService getRateFromUrlService) {
        this.getRateFromUrlService = getRateFromUrlService;
    }

    @Override
    public BigDecimal exchangeCurrencies(String currencyFrom, String currencyTo, double amount) throws InvalidDataInputException, IOException {
        if (!currencyFrom.matches("[A-Z]{3}") || !currencyTo.matches("[A-Z]{3}") || amount < 0) {
            throw new InvalidDataInputException("Invalid input data");
        }
        BigDecimal rate = getRateFromUrlService.getRate(currencyFrom, currencyTo);
        return rate.multiply(BigDecimal.valueOf(amount));
    }


}
