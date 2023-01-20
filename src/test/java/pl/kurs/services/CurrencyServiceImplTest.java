package pl.kurs.services;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.exceptions.InvalidDataInputException;
import pl.kurs.interfaces.GetRateFromUrlService;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CurrencyServiceImplTest {

    private BigDecimal SAMPLE_RATE = new BigDecimal("3.69975");
    @Mock
    private GetRateFromUrlService getRateFromUrlService;
    private CurrencyServiceImpl currencyService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyServiceImpl(getRateFromUrlService);
    }

    @Test
    public void shouldReturn369Dot975ForAmount100() throws IOException {
        Mockito.when(getRateFromUrlService.getRate(Mockito.anyString(), Mockito.anyString())).thenReturn(SAMPLE_RATE);

        BigDecimal expected = new BigDecimal("369.975");
        BigDecimal actual = currencyService.exchangeCurrencies("USD", "AED", 100);

        assertEquals(expected, actual.setScale(3));
    }

    @Test
    public void shouldThrowInvalidDataInputExceptionWhenCurrencyFromIsInvalid() {
        Throwable throwable = new InvalidDataInputException("Invalid input data");
        Throwable e = assertThrows(InvalidDataInputException.class, () -> currencyService.exchangeCurrencies("ADASD", "PLN", 100));

        assertEquals(InvalidDataInputException.class, e.getClass());

        Assertions.assertThat(e)
                .hasSameClassAs(throwable)
                .hasMessage("Invalid input data");
    }

    @Test
    public void shouldThrowInvalidDataInputExceptionWhenCurrencyToIsInvalid() {
        Throwable throwable = new InvalidDataInputException("Invalid input data");
        Throwable e = assertThrows(InvalidDataInputException.class, () -> currencyService.exchangeCurrencies("USD", "ADASD", 100));

        assertEquals(InvalidDataInputException.class, e.getClass());

        Assertions.assertThat(e)
                .hasSameClassAs(throwable)
                .hasMessage("Invalid input data");
    }

    @Test
    public void shouldThrowInvalidDataInputExceptionWhenAmountIsInvalid() {
        Throwable throwable = new InvalidDataInputException("Invalid input data");
        Throwable e = assertThrows(InvalidDataInputException.class, () -> currencyService.exchangeCurrencies("USD", "PLN", -1));

        assertEquals(InvalidDataInputException.class, e.getClass());

        Assertions.assertThat(e)
                .hasSameClassAs(throwable)
                .hasMessage("Invalid input data");
    }
}