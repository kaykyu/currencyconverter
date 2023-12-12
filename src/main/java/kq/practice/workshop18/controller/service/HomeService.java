package kq.practice.workshop18.controller.service;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import kq.practice.workshop18.config.Utilis;
import kq.practice.workshop18.model.Currency;
import kq.practice.workshop18.model.Request;

@Service
public class HomeService {

    @Autowired
    Utilis ut;

    RestTemplate template = new RestTemplate();

    public List<Currency> getCurr() {

        String url = UriComponentsBuilder
                .fromUriString(Utilis.url + Utilis.currency)
                .queryParam("apiKey", ut.apiKey)
                .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        JsonReader jReader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject curr = jReader.readObject().get("results").asJsonObject();

        List<Currency> list = curr.keySet().stream()
                .map(key -> curr.getJsonObject(key))
                .map(obj -> new Currency(obj.getString("id"), obj.getString("currencyName"),
                        obj.getString("currencySymbol", " ")))
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .toList();

        return list;
    }

    public Double getConvert(Request request) {

        String key = "%s_%s".formatted(request.getCurrFrom(), request.getCurrTo());

        String url = UriComponentsBuilder
                .fromUriString(Utilis.url + Utilis.convert)
                .queryParam("q", key)
                .queryParam("compact", "ultra")
                .queryParam("apiKey", ut.apiKey)
                .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        JsonReader jReader = Json.createReader(new StringReader(resp.getBody()));
        Double conversion = Double.parseDouble(jReader.readObject().get(key).toString());
        return request.getAmount() * conversion;
    }
    
    public Currency findCurr(String id) {

        List<Currency> list = getCurr();
        for (Currency curr : list) {
            if (curr.getId().equals(id)) {
                return curr;
            }
        }

        return null;
    }
}
