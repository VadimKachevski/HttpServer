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
        server.createContext("/comments",new comments());
        server.createContext("/addcomment",new addcomment());
        server.createContext("/addquestion",new addquestion());
        server.createContext("/registar",new registar());
        server.createContext("/login",new login());
        server.start();
    }


    public static class login implements HttpHandler
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

                        String mail="";
                        List<String> mailL = requestParameters.get("mail");
                        //String head="";
                        for (String s :
                                mailL) {
                            mail+=s;
                        }
                        List<String> passL = requestParameters.get("pass");
                        String pass="";
                        for (String s :
                                passL) {
                            pass+=s;
                        }

                        dbHandler.users userAL = dbConnector.login(mail,pass);
                        //ObjectMapper mapper = new ObjectMapper();
                        ObjectMapper mapper = new ObjectMapper();
                        //mapper.
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



    private static class registar implements HttpHandler
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

                        String mail="";
                        List<String> mailL = requestParameters.get("mail");
                        //String head="";
                        for (String s :
                                mailL) {
                            mail+=s;
                        }
                        List<String> passL = requestParameters.get("pass");
                        String pass="";
                        for (String s :
                                passL) {
                            pass+=s;
                        }

                        List<String> firstNameL = requestParameters.get("firstName");
                        String firstName="";
                        for (String s :
                                firstNameL) {
                            firstName+=s;
                        }
                        List<String> LastNameL = requestParameters.get("lastName");
                        String lastName="";
                        for (String s :
                                LastNameL) {
                            lastName+=s;
                        }

                        String userAL = dbConnector.registar(mail,pass,firstName,lastName);
                        //ObjectMapper mapper = new ObjectMapper();
                        String responseBody = userAL;




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

    private static class addcomment implements HttpHandler
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


                        List<String> idq = requestParameters.get("idquestions");
                        String quid="";
                        if(idq.size() >= 0)
                        {
                            quid = idq.get(0);
                        }

                        List<String> txts = requestParameters.get("txt");
                        String txt="";
//                        if(txts.size() >= 0) {
//                            txt = txts.get(0);
//                        }
                        for (String s :
                                txts) {
                            txt+=s;
                        }
                        List<String> idCourseL = requestParameters.get("idCourse");
                        String idCourse="";
                        if(idCourseL.size() >= 0) {
                            idCourse = idCourseL.get(0);
                        }
                        List<String> nameL = requestParameters.get("name");
                        String name="";
                        if(nameL.size() >= 0) {
                            name = nameL.get(0);
                        }
//                        List<String> fileL = requestParameters.get("file");
                        List<String> fileS = requestParameters.get("file2");
//                        String file="0";
//                        if(fileL.size() >= 0) {
//                            file = fileL.get(0);
//                        }
                        String file = "";
//                        for (String t :
//                                fileL) {
//                            file = file+t;
//                        }
//                        file = file+";";
                        for (String t :
                                fileS) {
                            file = file+t;

                        }
                        if(file.length()  > 7) {
                            file = file.substring(7);
                            file = file.replaceAll(" ", "+");
                        }
                        ArrayList<dbHandler.questions> userAL = dbConnector.setComment(quid,txt,idCourse,name,file);
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

    private static class addquestion implements HttpHandler
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
//                        List<String> degreeIDL = requestParameters.get("degreeID");
//                       String degid = "";
//                        for (String s :
//                                degreeIDL) {
//                            degid+=s;
//                        }
                        List<String> txts = requestParameters.get("txt");
                        String txt="";
//                        if(txts.size() >= 0) {
//                            txt = txts.get(0);
//                        }
                        for (String s :
                                txts) {
                            txt+=s;
                        }
                        List<String> idCourseL = requestParameters.get("idCourse");
                        String idCourse="";
                        if(idCourseL.size() >= 0) {
                            idCourse = idCourseL.get(0);
                        }
                        List<String> nameL = requestParameters.get("name");
                        String name="";
                        if(nameL.size() >= 0) {
                            name = nameL.get(0);
                        }
//                        List<String> fileL = requestParameters.get("file");
                        List<String> fileS = requestParameters.get("file2");
//                        String file="0";
//                        if(fileL.size() >= 0) {
//                            file = fileL.get(0);
//                        }
                        String file = "";
//                        for (String t :
//                                fileL) {
//                            file = file+t;
//                        }
//                        file = file+";";
                        for (String t :
                            fileS) {
                        file = file+t;

                    }
                       // List<String> typeL = requestParameters.get("type");
                        String type = "'מבחן'";
//                        if(typeL.size() >=0)
//                        {
//                            type = typeL.get(0);
//                        }
                        if(file.length()  > 7) {
                            file = file.substring(7);
                            file = file.replaceAll(" ", "+");
                        }
                        ArrayList<dbHandler.questions> userAL = dbConnector.addQuestion(txt,idCourse,name,file,type);
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


    private static class comments implements HttpHandler
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
                        List<String> lstring = requestParameters.get("idquestions");
                        String head="0";
                        if(lstring.size() >= 0) {
                            head = lstring.get(0);
                        }
                        ArrayList<dbHandler.questions> userAL = dbConnector.getComments(head);
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

                        String degreeID="";
                        List<String> degreeID1 = requestParameters.get("degreeID");
                        //String head="";
                        for (String s :
                                degreeID1) {
                            degreeID+=s;
                        }
                        List<String> lstring = requestParameters.get("courseName");
                        String head="";
                        for (String s :
                                lstring) {
                            head+=s;
                        }
                        ArrayList<dbHandler.questions> userAL = dbConnector.getQuestions(head,degreeID);
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