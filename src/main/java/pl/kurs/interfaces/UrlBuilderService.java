package pl.kurs.interfaces;

public interface UrlBuilderService {

    String getUrl(String baseLink, String pageEndpoint, String currency, String apiKey);

}
