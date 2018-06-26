package be.swsb.pubgclient;

import static be.swsb.pubgclient.PubgApiClient.DEFAULT_API_BASE_URL;

public class PubgApiClientBuilder {

    private String apiKey;
    private String apiBaseUrl;

    public PubgApiClient build() {
        if (!isEmpty(apiKey) && !isEmpty(apiBaseUrl)) {
            return new PubgApiClient(apiBaseUrl, apiKey);
        }
        if (isEmpty(apiKey) && !isEmpty(apiBaseUrl)) {
            return new PubgApiClient(apiBaseUrl);
        }
        if (!isEmpty(apiKey) && isEmpty(apiBaseUrl)) {
            return new PubgApiClient(DEFAULT_API_BASE_URL, apiKey);
        }
        return new PubgApiClient(DEFAULT_API_BASE_URL);
    }

    public PubgApiClientBuilder withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public PubgApiClientBuilder withApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
        return this;
    }

    private boolean isEmpty(String input) {
        return input == null || input.equals("");
    }
}
