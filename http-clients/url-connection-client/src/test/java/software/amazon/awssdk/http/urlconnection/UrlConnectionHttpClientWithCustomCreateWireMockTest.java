package software.amazon.awssdk.http.urlconnection;

import static software.amazon.awssdk.utils.FunctionalUtils.invokeSafely;

import java.net.HttpURLConnection;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.SdkHttpClientTestSuite;

public final class UrlConnectionHttpClientWithCustomCreateWireMockTest extends SdkHttpClientTestSuite {
    @Override
    protected SdkHttpClient createSdkHttpClient(SdkHttpClientOptions options) {
        return UrlConnectionHttpClient.create((uri) -> invokeSafely(() -> (HttpURLConnection) uri.toURL().openConnection()));
    }
}
