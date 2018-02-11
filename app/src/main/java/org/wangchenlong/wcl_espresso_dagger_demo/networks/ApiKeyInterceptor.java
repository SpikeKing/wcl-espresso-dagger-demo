package org.wangchenlong.wcl_espresso_dagger_demo.networks;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * 在请求中插入验证信息
 * <p>
 * Created by wangchenlong on 16/1/12.
 */
public class ApiKeyInterceptor implements Interceptor {

    private static final String QUERY_APP_ID = "APPID";

    private final String mApiKey;

    public ApiKeyInterceptor(String apiKey) {
        mApiKey = apiKey;
    }

    // 插入参数
    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.httpUrl().newBuilder().addQueryParameter(QUERY_APP_ID, mApiKey).build();
        Request newRequest = chain.request().newBuilder().url(url).build();
        return chain.proceed(newRequest);
    }
}
