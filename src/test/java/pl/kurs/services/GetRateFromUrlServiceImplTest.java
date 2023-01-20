package pl.kurs.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.interfaces.UrlBuilderService;
import pl.kurs.utils.ObjectMapperHolder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.Assert.*;

public class GetRateFromUrlServiceImplTest {

    private String SAMPLE_API_RESPONSE = "{\"base\":\"USD\",\"results\":{\"AED\":3.67269,\"AFN\":87.36987,\"ALL\":111.13063,\"AMD\":396.16667,\"ANG\":1.7856,\"AOA\":505.73161,\"ARS\":169.87187,\"AUD\":1.47768,\"AWG\":1.79776,\"AZN\":1.69808,\"BAM\":1.86048,\"BBD\":1.99921,\"BDT\":103.25396,\"BGN\":1.85726,\"BHD\":0.37582,\"BIF\":2057.94532,\"BMD\":0.99927,\"BND\":1.35332,\"BOB\":6.93137,\"BRL\":5.25661,\"BSD\":1.00092,\"BTN\":82.84194,\"BWP\":12.9172,\"BZD\":2.01353,\"CAD\":1.36607,\"CDF\":1998.73339,\"CHF\":0.93556,\"CLF\":0.02426,\"CLP\":861.75985,\"CNH\":6.97607,\"CNY\":6.97604,\"COP\":4802.94017,\"CUP\":24.00641,\"CVE\":105.2266,\"CZK\":23.06912,\"DJF\":177.77469,\"DKK\":7.06212,\"DOP\":55.01495,\"DZD\":138.14617,\"EGP\":24.66226,\"ERN\":15.14333,\"ETB\":53.49731,\"EUR\":0.94931,\"FJD\":2.20002,\"FKP\":0.81717,\"GBP\":0.81717,\"GEL\":2.68567,\"GHS\":12.4408,\"GIP\":0.81717,\"GMD\":63.35594,\"GNF\":8590.79543,\"GTQ\":7.88431,\"GYD\":209.21356,\"HKD\":7.76959,\"HNL\":24.61389,\"HRK\":7.13671,\"HTG\":144.4212,\"HUF\":398.65903,\"IDR\":15611.9764,\"ILS\":3.43418,\"INR\":82.57146,\"IQD\":1456.68197,\"IRR\":41998.13686,\"ISK\":142.34233,\"JMD\":153.43128,\"JOD\":0.70772,\"JPY\":136.94412,\"KES\":122.4198,\"KGS\":84.95841,\"KHR\":4121.99487,\"KMF\":466.75274,\"KPW\":901.79286,\"KRW\":1304.87685,\"KWD\":0.30633,\"KYD\":0.81905,\"KZT\":468.65674,\"LAK\":17228.2511,\"LBP\":1508.37596,\"LKR\":367.91312,\"LRD\":154.24927,\"LSL\":17.31798,\"LYD\":4.8342,\"MAD\":10.5457,\"MDL\":19.32142,\"MGA\":4392.50168,\"MKD\":58.55623,\"MMK\":2093.41854,\"MNT\":3428.49788,\"MOP\":7.99868,\"MRU\":37.87152,\"MUR\":44.28155,\"MVR\":15.64965,\"MWK\":1020.77239,\"MXN\":19.78688,\"MYR\":4.41375,\"MZN\":63.65903,\"NAD\":17.25914,\"NGN\":443.58524,\"NOK\":10.01732,\"NPR\":132.02886,\"NZD\":1.56582,\"OMR\":0.38387,\"PAB\":0.99895,\"PEN\":3.82826,\"PGK\":3.51807,\"PHP\":55.67057,\"PKR\":224.59428,\"PLN\":4.45378,\"PYG\":7103.57592,\"QAR\":3.63741,\"RON\":4.67254,\"RSD\":111.06689,\"RUB\":62.72117,\"RWF\":1059.11386,\"SAR\":3.76007,\"SCR\":14.40535,\"SDG\":564.93128,\"SEK\":10.34983,\"SGD\":1.35421,\"SHP\":0.81717,\"SLL\":18466.42261,\"SOS\":568.48023,\"SRD\":31.48373,\"SYP\":2509.96944,\"SZL\":17.31095,\"THB\":34.73875,\"TJS\":10.19432,\"TMT\":3.50229,\"TND\":3.21196,\"TOP\":2.36563,\"TRY\":18.64775,\"TTD\":6.75649,\"TWD\":30.73169,\"TZS\":2333.60027,\"UAH\":36.67625,\"UGX\":3686.60827,\"USD\":1,\"UYU\":39.04481,\"UZS\":11270.04102,\"VND\":23657.14939,\"VUV\":117.19074,\"WST\":2.68126,\"XAF\":622.81285,\"XCD\":2.69928,\"XDR\":0.75418,\"XOF\":622.81285,\"XPF\":113.22013,\"YER\":249.64126,\"ZAR\":17.3098,\"ZMW\":17.49367},\"updated\":\"2022-12-12 09:21:23\",\"ms\":6}\n";

    private JsonNode jsonNode;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private UrlBuilderService urlBuilderService;
    private GetRateFromUrlServiceImpl getRateFromUrlService;

    @Before
    public void init() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);
        getRateFromUrlService = new GetRateFromUrlServiceImpl(urlBuilderService, objectMapper);
    }

    @Test
    public void shouldReturn3Dot67269ForCurrencyFromUSDToAED() throws IOException {
        jsonNode = ObjectMapperHolder.INSTANCE.getMapper().readTree(SAMPLE_API_RESPONSE);
        Mockito.when(urlBuilderService.getUrl(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("https://wp.pl");

        Mockito.when(objectMapper.readTree(new URL("https://wp.pl"))).thenReturn(jsonNode);

        BigDecimal expected = new BigDecimal("3.67269");
        BigDecimal actual = getRateFromUrlService.getRate("USD", "AED");
        assertEquals(expected, actual);
    }

}