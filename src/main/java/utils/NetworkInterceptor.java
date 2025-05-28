package utils;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v117.network.Network;
import org.openqa.selenium.devtools.v117.network.model.Response;

public class NetworkInterceptor {
    private DevTools devTools;
    private static Logger LOGGER = LogManager.getLogger(NetworkInterceptor.class);
    private String bearerToken;

    public NetworkInterceptor(DevTools devTools) {
        if (devTools == null) {
            throw new IllegalStateException("DevTools is null");
        }
        this.devTools = devTools;
        devTools.createSession();
    }

    public void startIntercepting() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.responseReceived(), responseEvent -> {
            Response response = responseEvent.getResponse();
            Map<String, Object> headers = response.getHeaders();

            LOGGER.info("Response URL: " + response.getUrl());
            LOGGER.info("Response Status: " + response.getStatus());

            if (headers != null && headers.containsKey("Authorization")) {
                String authHeader = headers.get("Authorization").toString();
                if (authHeader.startsWith("Bearer ")) {
                    bearerToken = authHeader.replace("Bearer ", "").trim();
                    LOGGER.info("Captured Bearer Token: " + bearerToken);
                }
            }
        });
    }

    public String getBearerToken() {
        return bearerToken;
    }
}
