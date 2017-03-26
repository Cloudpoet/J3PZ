package cn.elbereth.j3pz.wizard;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import cn.elbereth.j3pz.base.PzApplication;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/20.
 */

class HttpHelper {
    private static final String TAG = "HttpHelper";

    private final OkHttpClient client;
    private final Gson gson;

    HttpHelper() {
        gson = new Gson();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        InputStream certificate = null;
        try {
            certificate = PzApplication.getInstance().getAssets().open("j3pz.cer");
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                try {
                    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    keyStore.load(null);
                    keyStore.setCertificateEntry("0", certificateFactory.generateCertificate(certificate));

                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init(keyStore);
                    sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                    builder.sslSocketFactory(sslContext.getSocketFactory());
                } catch (GeneralSecurityException e) {
                    Log.e(TAG, "HttpHelper: failed to generate ssl connection", e);
                }
            } catch (CertificateException e) {
                Log.e(TAG, "HttpHelper: failed to generate factory", e);
            }
        } catch (IOException e) {
            Log.e(TAG, "HttpHelper: failed to open certificate", e);
        } finally {
            if (certificate != null) {
                try {
                    certificate.close();
                } catch (IOException e) {
                    Log.e(TAG, "HttpHelper: failed to close io", e);
                }
            }
        }

        client = builder.build();
    }

    void get(String url, Map<String, String> map, Callback callback) {
        Request.Builder builder = new Request.Builder().url(url).get();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    void get(String url, Callback callback) {
        get(url, null, callback);
    }

    void pramGet(String url, Map<String, String> map, Callback callback) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        HttpUrl httpUrl = builder.build();
        Request request = new Request.Builder().url(httpUrl).get().build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    void post(String url, RequestBody body, Callback callback) {
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    RequestBody getJsonBody(JsonObject object) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(object));
    }
}
