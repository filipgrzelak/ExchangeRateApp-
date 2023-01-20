package pl.kurs.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.constans.Constans;
import pl.kurs.exceptions.InvalidDataInputException;
import pl.kurs.interfaces.GetRateFromUrlService;
import pl.kurs.interfaces.UrlBuilderService;
import pl.kurs.utils.ObjectMapperHolder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

public class GetRateFromUrlServiceImpl implements GetRateFromUrlService {

    private ObjectMapper mapper;
    private UrlBuilderService urlBuilderService;

    public GetRateFromUrlServiceImpl(UrlBuilderService urlBuilderService, ObjectMapper mapper) {
        this.urlBuilderService = urlBuilderService;
        this.mapper = mapper;
    }

    @Override
    public BigDecimal getRate(String currencyFrom, String currencyTo) throws InvalidDataInputException, IOException {
        String url = urlBuilderService.getUrl(Constans.BASE_LINK, Constans.PAGE_ENDPOINT, currencyFrom, Constans.API_KEY);

        JsonNode mainNode = mapper.readTree(new URL(url));
        JsonNode result = mainNode.get("results").get(currencyTo);
        return result.decimalValue();
    }


}
