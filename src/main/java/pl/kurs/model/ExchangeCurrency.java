package pl.kurs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ExchangeCurrency implements Serializable {

    private static final long serialVersionUID = 3331428134823423412L;

    @Id
    @GeneratedValue
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private LocalDateTime timeOfExchange;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal resultOfTheOperation;

    public ExchangeCurrency() {
    }

    public ExchangeCurrency(String currencyFrom, String currencyTo, BigDecimal rate, BigDecimal amount, BigDecimal resultOfTheOperation) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
        this.amount = amount;
        this.resultOfTheOperation = resultOfTheOperation;
        this.timeOfExchange = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeCurrency that = (ExchangeCurrency) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (currencyFrom != null ? !currencyFrom.equals(that.currencyFrom) : that.currencyFrom != null) return false;
        if (currencyTo != null ? !currencyTo.equals(that.currencyTo) : that.currencyTo != null) return false;
        if (timeOfExchange != null ? !timeOfExchange.equals(that.timeOfExchange) : that.timeOfExchange != null)
            return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return resultOfTheOperation != null ? resultOfTheOperation.equals(that.resultOfTheOperation) : that.resultOfTheOperation == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currencyFrom != null ? currencyFrom.hashCode() : 0);
        result = 31 * result + (currencyTo != null ? currencyTo.hashCode() : 0);
        result = 31 * result + (timeOfExchange != null ? timeOfExchange.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (resultOfTheOperation != null ? resultOfTheOperation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExchangeCurrency{" +
                "id=" + id +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", timeOfExchange=" + timeOfExchange +
                ", rate=" + rate +
                ", amount=" + amount +
                ", resultOfTheOperation=" + resultOfTheOperation +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public LocalDateTime getTimeOfExchange() {
        return timeOfExchange;
    }

    public void setTimeOfExchange(LocalDateTime timeOfExchange) {
        this.timeOfExchange = timeOfExchange;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getResultOfTheOperation() {
        return resultOfTheOperation;
    }

    public void setResultOfTheOperation(BigDecimal resultOfTheOperation) {
        this.resultOfTheOperation = resultOfTheOperation;
    }
}
