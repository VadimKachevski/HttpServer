import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dbHandler.degree;
import dbHandler.users;
import dbHandler.dbConnector;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class httpMain {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;
    private static final int BACKLOG = 1;
    private static final String HEADER_ALLOW = "Allow";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int STATUS_OK = 200;
    private static final int STATUS_METHOD_NOT_ALLOWED = 405;
    private static final int NO_RESPONSE_LENGTH = -1;
    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String ALLOWED_METHODS = METHOD_GET + "," + METHOD_OPTIONS;

    public static void main(final String... args) throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), BACKLOG);
        server.createContext("/degree",new degree());
        server.createContext("/users",new users());
        server.createContext("/course",new course());
        server.createContext("/question",new question());
        server.start();
    }

    private static class question implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                final Headers headers = exchange.getResponseHeaders();

                final String requestMethod = exchange.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET:
                        final Map<String, List<String>> requestParameters = getRequestParameters(exchange.getRequestURI());
                        // do something with the request parameters
                        ArrayList<dbHandler.questions> userAL = dbConnector.getQuestions();
                        ObjectMapper mapper = new ObjectMapper();
                        String responseBody = mapper.writeValueAsString(userAL);
                        headers.set("Access-Control-Allow-Origin","*");
                        headers.set("Access-Control-Allow-Credentials","true");
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        exchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        exchange.getResponseBody().write(rawResponseBody);
                        exchange.getResponseBody().write("test".getBytes());
                        break;
                    case METHOD_OPTIONS:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                        break;
                    default:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                        break;
                }
            } finally {
                exchange.close();
            }

        }
    }

    private static class course implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                final Headers headers = exchange.getResponseHeaders();

                final String requestMethod = exchange.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET:
                        final Map<String, List<String>> requestParameters = getRequestParameters(exchange.getRequestURI());
                        List<String> lstring = requestParameters.get("degreeName");
                       String head="0";
                        if(lstring.size() >= 0) {
                             head = lstring.get(0);
                        }
                        ArrayList<dbHandler.course> userAL = dbConnector.getCourses(head);
                        ObjectMapper mapper = new ObjectMapper();
                        String responseBody = mapper.writeValueAsString(userAL);
                        headers.set("Access-Control-Allow-Origin","*");
                        headers.set("Access-Control-Allow-Credentials","true");
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        exchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        exchange.getResponseBody().write(rawResponseBody);
                        exchange.getResponseBody().write("test".getBytes());
                        break;
                    case METHOD_OPTIONS:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                        break;
                    default:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                        break;
                }
            } finally {
                exchange.close();
            }

        }
    }

    private static class users implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                final Headers headers = exchange.getResponseHeaders();

                final String requestMethod = exchange.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET:
                        final Map<String, List<String>> requestParameters = getRequestParameters(exchange.getRequestURI());
                        ArrayList<dbHandler.users> userAL = dbConnector.getUsers();
                        ObjectMapper mapper = new ObjectMapper();
                        String responseBody = mapper.writeValueAsString(userAL);
                        headers.set("Access-Control-Allow-Origin","*");
                        headers.set("Access-Control-Allow-Credentials","true");
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        exchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        exchange.getResponseBody().write(rawResponseBody);
                        exchange.getResponseBody().write("test".getBytes());
                        break;
                    case METHOD_OPTIONS:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                        break;
                    default:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                        break;
                }
            } finally {
                exchange.close();
            }
        }
    }


    private static class degree implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                final Headers headers = exchange.getResponseHeaders();

                final String requestMethod = exchange.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET:
                        final Map<String, List<String>> requestParameters = getRequestParameters(exchange.getRequestURI());
                        // do something with the request parameters
                        ArrayList<dbHandler.degree> userAL = dbConnector.getDegree();
                        ObjectMapper mapper = new ObjectMapper();
                        String responseBody = mapper.writeValueAsString(userAL);
                        headers.set("Access-Control-Allow-Origin","*");
                        headers.set("Access-Control-Allow-Credentials","true");
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        exchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        exchange.getResponseBody().write(rawResponseBody);
                        exchange.getResponseBody().write("test".getBytes());
                        break;
                    case METHOD_OPTIONS:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                        break;
                    default:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        exchange.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                        break;
                }
            } finally {
                exchange.close();
            }
        }
    }

    private static Map<String, List<String>> getRequestParameters(final URI requestUri) {
        final Map<String, List<String>> requestParameters = new LinkedHashMap<>();
        final String requestQuery = requestUri.getRawQuery();
        if (requestQuery != null) {
            final String[] rawRequestParameters = requestQuery.split("[&;]", -1);
            for (final String rawRequestParameter : rawRequestParameters) {
                final String[] requestParameter = rawRequestParameter.split("=", 2);
                final String requestParameterName = decodeUrlComponent(requestParameter[0]);
                requestParameters.putIfAbsent(requestParameterName, new ArrayList<>());
                final String requestParameterValue = requestParameter.length > 1 ? decodeUrlComponent(requestParameter[1]) : null;
                requestParameters.get(requestParameterName).add(requestParameterValue);
            }
        }
        return requestParameters;
    }

    private static String decodeUrlComponent(final String urlComponent) {
        try {
            return URLDecoder.decode(urlComponent, CHARSET.name());
        } catch (final UnsupportedEncodingException ex) {
            throw new InternalError(ex);
        }
    }
}


//                        //****** FOR LATER IMG TO BASE 64
//                        String base64Image = "";
//                        File file = new File("src/main/resources/img1.jpg");
//                        try (FileInputStream imageInFile = new FileInputStream(file)) {
//                            // Reading a Image file from file system
//                            byte imageData[] = new byte[(int) file.length()];
//                            imageInFile.read(imageData);
//                            base64Image = Base64.getEncoder().encodeToString(imageData);
//                        } catch (FileNotFoundException e) {
//                            System.out.println("Image not found" + e);
//                        } catch (IOException ioe) {
//                            System.out.println("Exception while reading the Image " + ioe);
//                        }
//                        String responseBody = base64Image;
//                        //******** FOR LATER BASE 64