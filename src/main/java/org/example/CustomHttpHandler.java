package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public final class CustomHttpHandler implements HttpHandler {
    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();

        for (String param : query.split("&")) {
            String[] entry = param.split("=");

            if (entry.length > 1)
                result.put(entry[0], entry[1]);
        }

        return result;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                handleGet(exchange);
                break;
            default:
                exchange.sendResponseHeaders(405, -1);
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        String response;

        String query = exchange.getRequestURI().getQuery();
        if (query != null) {
            Map<String, String> queryMap = queryToMap(query);

            Shape shape = null;
            switch (queryMap.get("shape")) {
                case "rectangle":
                    if (queryMap.containsKey("width") && queryMap.containsKey("height")) {
                        try {
                            double width = Double.parseDouble(queryMap.get("width"));
                            double height = Double.parseDouble(queryMap.get("height"));

                            shape = new Rectangle(width, height);
                        } catch (NumberFormatException ignored) {
                            break;
                        }
                    }
                    break;
                case "square":
                    try {
                        double side;
                        if (queryMap.containsKey("width")) {
                            side = Double.parseDouble(queryMap.get("width"));
                        } else if (queryMap.containsKey("height")) {
                            side = Double.parseDouble(queryMap.get("height"));
                        } else {
                            break;
                        }

                        shape = new Square(side);
                    } catch (NumberFormatException ignored) { }
            }

            if (shape == null) {
                exchange.sendResponseHeaders(400, -1);
                return;
            }

            response = shape.toString();
        } else {
            exchange.sendResponseHeaders(400, -1);
            return;
        }

        exchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
