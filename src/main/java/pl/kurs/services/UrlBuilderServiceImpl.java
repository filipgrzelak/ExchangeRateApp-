package pl.kurs.services;

import pl.kurs.interfaces.UrlBuilderService;

public class UrlBuilderServiceImpl implements UrlBuilderService {

    @Override
    public String getUrl(String baseLink, String pageEndpoint, String currency, String apiKey) {
        StringBuilder sb = new StringBuilder(baseLink);
        sb.append(pageEndpoint)
                .append(currency)
                .append(apiKey);
        return sb.toString();
    }

}
