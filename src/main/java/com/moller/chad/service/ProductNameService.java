package com.moller.chad.service;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Map;

@Service
public class ProductNameService {
    HttpClient client;

    @Value("${product.name.host}")
    String hostName;
    @Value("${product.name.resource}")
    String resource;
    @Value("${product.name.parameters}")
    String parameters;
    @Value("${product.name.port}")
    int port;
    @Value("${product.name.protocol}")
    String protocol;

    @PostConstruct
    public void initialize() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        HttpHost host = new HttpHost(hostName, port);
        cm.setMaxPerRoute(new HttpRoute(host), 50);

        client = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    public String getName(long id) {
        try {
            URI uri = new URIBuilder()
                    .setScheme(protocol)
                    .setHost(hostName)
                    .setPath(resource + id)
                    .setCustomQuery(parameters)
                    .build();
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = client.execute(httpget);
            JsonParser parser = new JacksonJsonParser();

            String bodyString = IOUtils.toString(response.getEntity().getContent(), getContentEncoding(response));
            Map<String, Object> bodyMap = parser.parseMap(bodyString);
            Object product = mapGet(bodyMap, "product");
            Object item = mapGet(product, "item");
            Object productDescription = mapGet(item, "product_description");
            Object title = mapGet(productDescription, "title");

            if (title != null) {
                return title.toString();
            }
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Object mapGet(Object potentialMap, String key) {
        if (potentialMap instanceof Map) {
            return ((Map) potentialMap).get(key);
        }
        return null;
    }

    private String getContentEncoding(HttpResponse response) {
        Header encoding = response.getEntity().getContentEncoding();
        if (encoding == null || encoding.getValue() == null) {
            return "utf-8";
        }
        return encoding.getValue();
    }
}
