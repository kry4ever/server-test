package com.example.hasee_pc.localtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //10.2.197.251
        //192.168.199.188
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.197.251:8080") // 设置网络请求 Url
                //ScalarsConverterFactory
                .addConverterFactory(ScalarsConverterFactory.create()) //设置使用Gson解析
                .build();

        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerApi request = retrofit.create(ServerApi.class);
                //通过接口方法获取请求对象，里面对要发送的请求进行了封装
                Call<String> call = request.getUser("kry", "123456");
                //步骤5:发送网络请求(异步)
                call.enqueue(new Callback<String>() {
                    //请求成功时回调
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        // 步骤6：处理返回的数据结果
//                        Log.d("guyan", response.body().name);
                        if (response.isSuccessful()) {
                            String token = response.body();
                            if (token != null) {
                                Toast.makeText(MainActivity.this, token + "", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "empty token", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "token error!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //请求失败时回调
                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
//                        Log.e("guyan", "请求失败 " + throwable.getMessage());
                        Toast.makeText(MainActivity.this, "failed " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
