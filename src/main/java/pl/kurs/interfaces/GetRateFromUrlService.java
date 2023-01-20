package pl.kurs.interfaces;

import pl.kurs.exceptions.InvalidDataInputException;

import java.io.IOException;
import java.math.BigDecimal;

public interface GetRateFromUrlService {

    BigDecimal getRate(String currencyFrom, String currencyTo) throws InvalidDataInputException, IOException;

}
