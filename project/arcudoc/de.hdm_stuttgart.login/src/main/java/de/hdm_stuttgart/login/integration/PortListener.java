package de.hdm_stuttgart.login.integration;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import de.hdm_stuttgart.data.service.AccountInformation;
import de.hdm_stuttgart.data.service.NetworkStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PortListener {

    private static final Logger log = LogManager.getLogger();

    private final ObjectProperty<NetworkStatus> authStatus = new SimpleObjectProperty<>(NetworkStatus.DEFAULT);

    private HttpServer server;
    private boolean serverIsStarted = false;

    private String requestQuery;
    private String accessToken;
    private String expiresIn;
    private String providerToken;
    private String refreshToken;
    private long tokenTimestamp;


    public PortListener() {
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/arcudoc/login/", new ResponseHandler());
            server.setExecutor(Executors.newSingleThreadExecutor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * start the portListener
     */
    public void startListener() {
        if(!serverIsStarted){
            server.start();
            serverIsStarted = true;
            log.debug("PortListener started");
        }else{
            log.info("PortListener already started");
        }
    }

    /**
     * Parse the access tokens from the request url
     */
    private void parseTokens() throws MalformedTokensException {

        if (requestQuery != null && !requestQuery.isEmpty()) {

            String[] tokens = requestQuery.split("&");

            if (checkTokens(tokens)) {
                List<String> parsedTokens = Arrays.stream(tokens)
                        .map(s -> s.substring(s.indexOf('=') + 1))
                        .toList();

                accessToken = parsedTokens.get(0);
                expiresIn = parsedTokens.get(1);
                providerToken = parsedTokens.get(2);
                refreshToken = parsedTokens.get(3);
                tokenTimestamp = System.currentTimeMillis();

                setTokensToAccountInfos();
            }

        } else {
            log.error("Request Query is empty or null oder tokens are invalid: " + requestQuery);
           throw new MalformedTokensException("Request query empty or tokens are invalid");
        }
    }

    /**
     * checks if the token array contains all required tokens in the correct order
     *
     * @param tokens the array containing the tokens to check
     * @return true if all tokens are there and in the correct order, false if not
     */
    public boolean checkTokens(String[] tokens) throws MalformedTokensException {
        if (tokens.length == 5) {
            if (tokens[0].startsWith("access_token=")
                    && tokens[1].startsWith("expires_in=")
                    && tokens[2].startsWith("provider_token")
                    && tokens[3].startsWith("refresh_token=")
                    && tokens[4].startsWith("token_type")) {
                log.debug("Tokens valid");
                return true;
            }
        }

        log.error("Missing token, unable to parse tokens " + Arrays.toString(tokens));
        throw new MalformedTokensException("Unable to parse tokens. Tokens missing or wrong identifier.");
    }

    /**
     * sets the tokens and timestamps to the AccountInformation class
     */
    private void setTokensToAccountInfos(){
        AccountInformation.getInstance().setAccessToken(accessToken);
        AccountInformation.getInstance().setRefreshToken(refreshToken);
        AccountInformation.getInstance().setExpiresIn(Long.parseLong(String.valueOf(expiresIn)));
        authStatus.setValue(NetworkStatus.AUTH_SUCCESS);
        log.debug("Tokens set to AccountInformation");
    }

    /**
     * reads the response html site into a string
     * @return the string containing the whole html site
     */
    private String loadResponse() {
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("arcudoc.html");

            InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            return reader.lines().collect(Collectors.joining());
        }catch (NullPointerException exception){
            log.error(exception.getMessage());
        }
        return "<h6>Login successful, you can close the browser tab and return to arcudoc</h6>";
    }

    /**
     * Loads the response once an incoming connection to the according port is recognized
     */
    private class ResponseHandler implements HttpHandler {

        int reloads = 0;
        String response = loadResponse();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            log.debug("Received " + reloads + " connections");

            if(reloads == 0){ //on first load -> modify the request url to be able to get the access-tokens
                reloads++;
                String response =  """
                  <h1>loading</h1>
                  <script>
                        let url = window.location.href
                        let result = url.replace("#", "?")
                        location = result
                  </script>
                """;

                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }else if (reloads == 1){ //on reload (triggered by the above javascript) show the response html site and parse the tokens
                reloads++;

                requestQuery = exchange.getRequestURI().getQuery();

                try{
                    parseTokens();
                }catch (MalformedTokensException exception){
                    log.error(exception.getMessage());
                    response = "<h1>Login failed</h1>";
                }

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                server.stop(300);
                log.debug("Server stopped");
            }
        }
    }

    public ObjectProperty<NetworkStatus> getAuthStatusProperty() {
        return authStatus;
    }
}
