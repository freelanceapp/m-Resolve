package com.ibt.complaintmanagement.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.constant.Constant;
import com.ibt.complaintmanagement.modal.User;
import com.ibt.complaintmanagement.modal.user.UserMainModal;
import com.ibt.complaintmanagement.retrofit_provider.RetrofitService;
import com.ibt.complaintmanagement.retrofit_provider.WebResponse;
import com.ibt.complaintmanagement.utils.Alerts;
import com.ibt.complaintmanagement.utils.AppPreference;
import com.ibt.complaintmanagement.utils.BaseActivity;

import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    protected void init() {
        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.txtForgotPassword).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginApi();
                break;
            case R.id.txtForgotPassword:
                Intent iA = new Intent(mContext, ForgotPasswordActivity.class);
                startActivity(iA);
                finish();
                break;
        }
    }

    private void loginApi() {
        String strUsername = ((EditText) findViewById(R.id.edtUsername)).getText().toString();
        String strPassword = ((EditText) findViewById(R.id.edtPassword)).getText().toString();

        if (strUsername.isEmpty()) {
            Alerts.show(findViewById(R.id.rlMain), "Username can't be empty...!!!");
        } else if (strPassword.isEmpty()) {
            Alerts.show(findViewById(R.id.rlMain), "Password can't be empty...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getLoginData(new Dialog(mContext), retrofitApiClient.userLogin(strUsername, strPassword), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        UserMainModal userMainModal = (UserMainModal) result.body();
                        if (userMainModal == null)
                            return;
                        if (!userMainModal.getError()) {
                            Alerts.show(mContext, userMainModal.getMessage());
                            Gson gson = new GsonBuilder().setLenient().create();
                            String data = gson.toJson(userMainModal.getUser());
                            AppPreference.setBooleanPreference(mContext, Constant.IS_LOGIN, true);
                            AppPreference.setStringPreference(mContext, Constant.USER_DATA, data);
                            User.setUser(userMainModal.getUser());
                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Alerts.show(findViewById(R.id.rlMain), userMainModal.getMessage());
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(findViewById(R.id.rlMain), "Server error...!!!");
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }
}
