package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new Handler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");
        System.out.println(CustomMath.PI(1000000));
        System.out.println(CustomMath.greatestFactor(2048, 768));
    }

    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();

        for (String param : query.split("&")) {
            String[] entry = param.split("=");

            if (entry.length > 1)
                result.put(entry[0], entry[1]);
        }

        return result;
    }

    private static final class Handler implements HttpHandler {

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

    private static final class CustomMath {
        public static double PI(long iterations) {
            double result = 0;

            for (long i = 1; i <= iterations; i++) {
                result += 4.0 / (i * 2 - 1) * (i % 2 == 1 ? 1 : -1);
            }

            return result;
        }

        public static long greatestFactor(long a, long b) {
            if (a == b)
                return a;
            else if (a > b)
                return greatestFactor(a - b, b);
            else
                return greatestFactor(a, b - a);
        }
    }
}