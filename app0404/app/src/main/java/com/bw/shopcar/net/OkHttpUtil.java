package com.bw.shopcar.net;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 15:36
 */
public class OkHttpUtil {
    public int HTTP_SUCCESS = 10;
    public int HTTP_FAIL = 1;
    public HttpUtilListener httpUtilListener;

    public OkHttpUtil get(String url) {
        doHttp(url, 0, null);
        return this;
    }

    public OkHttpUtil post(String url, FormBody.Builder bodyBuild) {
        doHttp(url, 1, bodyBuild);
        return this;
    }

    private void doHttp(String url, int i, FormBody.Builder bodyBuild) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (i == 0) {
            builder.get();
        } else {
            RequestBody body = bodyBuild.build();
            builder.post(body);
        }
        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = HTTP_FAIL;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.obj = response.body().string();
                message.what = HTTP_SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HTTP_SUCCESS) {
                String data = (String) msg.obj;
                httpUtilListener.success(data);
            } else {
                String error = (String) msg.obj;
                httpUtilListener.fail(error);
            }
        }
    };

    public void result(HttpUtilListener httpUtilListener) {
        this.httpUtilListener = httpUtilListener;
    }

    public interface HttpUtilListener {
        void success(String data);

        void fail(String error);
    }
}
