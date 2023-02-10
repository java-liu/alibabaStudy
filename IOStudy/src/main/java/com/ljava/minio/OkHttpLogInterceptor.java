package com.ljava.minio;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * okhttp拦截器,获取返回结果值
 */
@Slf4j
public class OkHttpLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        Stopwatch stopwatch = Stopwatch.createStarted();
        log.info(String.format("发送请求 %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        //这里不能直接使用response.body().string()的方式@输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错,需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        log.info(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                stopwatch.elapsed(TimeUnit.NANOSECONDS) / 1e6d,
                response.headers()));

        return response;
    }
}
