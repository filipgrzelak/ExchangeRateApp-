package pl.kurs;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.dao.services.ExchangeCurrencyDaoImpl;
import pl.kurs.model.ExchangeCurrency;
import pl.kurs.services.CurrencyServiceImpl;
import pl.kurs.services.GetRateFromUrlServiceImpl;
import pl.kurs.services.UrlBuilderServiceImpl;
import pl.kurs.utils.ObjectMapperHolder;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        UrlBuilderServiceImpl urlBuilderService = new UrlBuilderServiceImpl();

        GetRateFromUrlServiceImpl getRateFromUrlService = new GetRateFromUrlServiceImpl(urlBuilderService, ObjectMapperHolder.INSTANCE.getMapper());

        CurrencyServiceImpl currencyService = new CurrencyServiceImpl(getRateFromUrlService);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Currency from: ");
        String currencyFrom = scanner.next();
        System.out.println();
        System.out.print("Currency to: ");
        String currencyTo = scanner.next();
        System.out.println();
        System.out.print("Amount: ");
        String amount = scanner.next();
        Double amountParse = Double.parseDouble(amount);
        BigDecimal resultOfOperation = currencyService.exchangeCurrencies(currencyFrom.toUpperCase(), currencyTo.toUpperCase(), amountParse);

        System.out.println("Result: " + resultOfOperation);

        BigDecimal rate = resultOfOperation.divide(BigDecimal.valueOf(amountParse), 10, RoundingMode.FLOOR);

        ExchangeCurrency exchangeCurrency = new ExchangeCurrency(currencyFrom,
                currencyTo,
                rate,
                BigDecimal.valueOf(amountParse),
                resultOfOperation);

        ExchangeCurrencyDaoImpl exchangeCurrencyDao = new ExchangeCurrencyDaoImpl();
        exchangeCurrencyDao.save(exchangeCurrency);
        exchangeCurrencyDao.cleanUp();
    }
}
