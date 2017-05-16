package com.alisir.moduledemo02;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alisir.base.SchameFilterActivity;

@Route(path = "/test/main")
public class MainActivity extends Activity {
    String TAG = "main程序";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(getApplication());

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "onCreate: 准备加载！");
                ARouter.getInstance()
                        .build("/test/activity")
                        .withString("name","老王")
                        .navigation(MainActivity.this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.i(TAG, "onArrival: ---------");
                            }

                            @Override
                            public void onFound(Postcard postcard) {
                                super.onFound(postcard);
                                Log.i(TAG, "onFound: ------------");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                super.onLost(postcard);
                                Log.i(TAG, "onLost: -----------");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                super.onInterrupt(postcard);
                                Log.i(TAG, "onInterrupt: ------------");
                            }
                        });

                findViewById(R.id.btn_into_core).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build("/main/lgsu")
                                .navigation(MainActivity.this, new NavCallback() {
                                    @Override
                                    public void onArrival(Postcard postcard) {
                                        Log.i(TAG, "onArrival: --------");
                                    }

                                    @Override
                                    public void onFound(Postcard postcard) {
                                        super.onFound(postcard);
                                        Log.i(TAG, "onFound: ---------");
                                    }

                                    @Override
                                    public void onLost(Postcard postcard) {
                                        super.onLost(postcard);
                                        Log.i(TAG, "onLost: --------");
                                    }

                                    @Override
                                    public void onInterrupt(Postcard postcard) {
                                        super.onInterrupt(postcard);
                                        Log.i(TAG, "被拦截了，好尴尬！");
                                        Toast.makeText(MainActivity.this,"请登录！",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), SchameFilterActivity.class);
//                intent.setData(Uri.parse("/test/activity"));
//                intent.putExtra("name","老王");
//                startActivity(intent);
            }
        });
}
}
