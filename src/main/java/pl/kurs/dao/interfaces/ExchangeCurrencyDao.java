package pl.kurs.dao.interfaces;

import pl.kurs.model.ExchangeCurrency;

public interface ExchangeCurrencyDao {

    void save(ExchangeCurrency exchangeCurrency);

    ExchangeCurrency load(Long id);

    void cleanUp();
}
