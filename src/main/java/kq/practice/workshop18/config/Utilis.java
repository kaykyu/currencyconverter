package kq.practice.workshop18.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utilis {

    @Value("${currconv.apikey}")
    public String apiKey;

    final public static String url = "https://free.currconv.com/api/v7";
    final public static String currency = "/currencies";
    final public static String convert = "/convert";
}