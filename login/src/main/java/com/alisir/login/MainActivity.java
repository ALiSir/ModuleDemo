package com.alisir.login;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alisir.base.BaseContent;
import com.alisir.base.net.NetUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

@Route(path = "/test/activity")
public class MainActivity extends Activity {

    @Autowired
    String name;

    CheckBox chckBtn;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        ARouter.getInstance().inject(this);

        ((TextView)findViewById(R.id.textView2)).setText(name);
        Log.i("姓名", "onCreate: "+name);

        chckBtn = (CheckBox) findViewById(R.id.login_checkBox);
        btn = (Button) findViewById(R.id.login_button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (chckBtn.isChecked()) {
                    Toast.makeText(getApplicationContext(),"登录成功！",Toast.LENGTH_SHORT).show();
                    Map<String,String> request = new HashMap<String, String>();
                    request.put("type","Login_request_post");
                    request.put("url","Api/login");
//                    NetUtil.init().postRequest(request);
                    NetUtil.init().postRequetRx(request, new Subscriber<HashMap<String, Object>>() {
                        @Override
                        public void onCompleted() {
                            Log.i("登录", "onCompleted: 登录完成！");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("登录", "onError: 登录失败！");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(HashMap<String, Object> stringObjectHashMap) {
                            Log.i("登录", "onCompleted: 登录成功！值："+stringObjectHashMap);
                        }
                    });


                    BaseContent.isLogin = true;
                }else{
                    Toast.makeText(getApplicationContext(),"登录失败！",Toast.LENGTH_SHORT).show();
                    BaseContent.isLogin = false;
                }
                finish();
            }
        });

        Log.i("loginMain", "onCreate: 加载完成！");
    }


}
