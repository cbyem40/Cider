package com.rachel.cider.service.impl;

import com.rachel.cider.Enum.District;
import com.rachel.cider.service.WeatherForecastService;
import com.rachel.cider.vo.ElementName;
import com.rachel.cider.vo.WeatheStation.WeatherElement;
import com.rachel.cider.vo.WeatheStation.WeatherStationsResponse;
import com.rachel.cider.vo.WeatherVO;
import com.restfb.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${application.connect.cwb.token}")
    private String accessToken;

    @Override
    public List<WeatherVO> getWeatherForecast(District district) throws UnsupportedEncodingException {

        StringJoiner sj = new StringJoiner(",");
        sj.add(ElementName.T.name()).add(ElementName.AT.name()).add(ElementName.PoP.name()).add(ElementName.RH.name());
        String elementName = sj.toString();

        String weatherStationUrl = "http://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-061";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(weatherStationUrl)
                // Add query parameter
                .queryParam("locationName", district.name())
                .queryParam("elementName", elementName)
                .queryParam("sort", "time");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", accessToken);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<WeatherStationsResponse> entity = new HttpEntity<>(headers);
        ResponseEntity<WeatherStationsResponse> response = restTemplate.exchange(uriBuilder.build(false).toUriString(), HttpMethod.GET, entity, WeatherStationsResponse.class);

        return parseResponse(response.getBody());
    }

    private List<WeatherVO> parseResponse(WeatherStationsResponse response){
        Set<WeatherVO> weatherVOs = new HashSet<>();
        List<WeatherElement> weatherElements = response.getRecords().getLocations().stream().map(locations -> locations.getLocation()).flatMap(x -> x.stream()).map(location -> location.getWeatherElement()).flatMap(y -> y.stream()).collect(Collectors.toList());

        for (WeatherElement we: weatherElements){
            we.getTime().stream().forEach(data -> {
                String startTime = !StringUtils.isBlank(data.getStartTime())? data.getStartTime() : data.getDataTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime parsedDate = LocalDateTime.parse(startTime, formatter);

                WeatherVO weatherVO = weatherVOs.stream().filter(vo -> parsedDate.equals(vo.getTime())).findFirst().orElse(new WeatherVO());
                weatherVO.setTime(parsedDate);
                if (we.getElementName() == ElementName.PoP)
                    weatherVO.setRainPercentage(data.getElementValue());
                else if (we.getElementName() == ElementName.AT)
                    weatherVO.setRealFeel(data.getElementValue());
                else if (we.getElementName() == ElementName.RH)
                    weatherVO.setHumidity(data.getElementValue());
                else
                    weatherVO.setTemp(data.getElementValue());
                weatherVOs.add(weatherVO);
            });
        }
        List<WeatherVO> sortedWeatherVOs = weatherVOs.stream().sorted(Comparator.comparing(WeatherVO::getTime)).collect(Collectors.toList());
        return sortedWeatherVOs;
    }
}
