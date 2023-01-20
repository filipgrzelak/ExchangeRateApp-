package pl.kurs.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlBuilderServiceImplTest {

    private UrlBuilderServiceImpl urlBuilderService;

    @Before
    public void init() {
        urlBuilderService = new UrlBuilderServiceImpl();
    }


    @Test
    public void shouldReturnConcatedStrings() {
        String base = "www.";
        String body = "wp";
        String end = ".pl";
        String extend = "/poczta";
        assertEquals("www.wp.pl/poczta",urlBuilderService.getUrl(base,body,end,extend));

    }

}