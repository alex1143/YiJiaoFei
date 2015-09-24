package com.example.dxkj.YiJiaoFei;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dxkj.bean.UserLonginResponse;
import com.example.dxkj.bean.Users;
import com.example.dxkj.cache.MapCache;
import com.example.dxkj.utils.Constant;
import com.example.dxkj.utils.HttpCallbackListener;
import com.example.dxkj.utils.HttpUtils;
import com.example.dxkj.utils.Md5;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {

    @ViewInject(R.id.iv)
    private ImageView iv;
    @ViewInject(R.id.btn_login)
    private Button btn_login;
    @ViewInject(R.id.btn_regist)
    private Button btn_regist;
    @ViewInject(R.id.edt_code)
    private TextInputLayout textInputCode;
    @ViewInject(R.id.edt_phone)
    private TextInputLayout textInputPhone;
    @ViewInject(R.id.top_head2)
    private ImageView imageView;
    private EditText edt_code;
    private EditText edt_phone;

    //存入登录信息
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        edt_code = textInputCode.getEditText();
        edt_phone = textInputPhone.getEditText();
        textInputCode.setHint("请输入密码");
        textInputPhone.setHint("请输入账号");
        edt_code.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        onClick();
        imageClick();
    }

    public void onClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str_code = edt_code.getText() + "";
                String str_phone = edt_phone.getText() + "";
                saveUserInfo(str_phone, str_code);                          //保存用户信息
                String params = "userid=" + str_phone + "&&userloginpwd="
                        + Md5.getMd5(str_code, true, "utf-8") + "&&userip=221.0.90.74";
                showProgressDialog();
                HttpUtils.sendHttpRequest(Constant.LONGIN_URL, params, new HttpCallbackListener() {

                    @Override
                    public void onFinish(String response) {
                        final String ddd = response;
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              Gson gson = new Gson();
                                              UserLonginResponse userLonginResponse = gson.fromJson(
                                                      ddd,
                                                      new TypeToken<UserLonginResponse>() {
                                                      }.getType());

                                              Users user = userLonginResponse.getEntity();
                                              user.setUserToken(userLonginResponse.getUserToken());
                                           /*   ACache cache= ACache.get(ContextUtil.getInstance());*/
                                              MapCache.cacheMap.put("userObject", user);
                                              startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                              finish();
                                              closeProgressDialog();
                                              Toast.makeText(LoginActivity.this,
                                                      "登录成功", Toast.LENGTH_SHORT).show();
                                          }
                                      }
                        );
                    }

                    @Override
                    public void onError(Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                closeProgressDialog();
                                Toast.makeText(LoginActivity.this,
                                        "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    ProgressDialog progressDialog = null;


    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在登录...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 保存用户登录信息
     */
    public void saveUserInfo(String userPhone, String userPassWord) {
        preferences = getSharedPreferences("loginNew", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //设置参数
        editor.putString("userphone", userPhone);
        editor.putString("password", userPassWord);
        //提交
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    //标签栏按钮设置
    public void imageClick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}

       /*   edt_phone.setError("HAHA");*/




