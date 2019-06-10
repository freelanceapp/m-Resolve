package com.ibt.complaintmanagement.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.retrofit_provider.RetrofitService;
import com.ibt.complaintmanagement.retrofit_provider.WebResponse;
import com.ibt.complaintmanagement.utils.Alerts;
import com.ibt.complaintmanagement.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();
    }

    protected void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.btnRequest).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnRequest:
                forgotRequestApi();
                break;
        }
    }

    private void forgotRequestApi() {
        String strUsername = ((EditText) findViewById(R.id.edtUsername)).getText().toString();
        if (strUsername.isEmpty()) {
            Alerts.show(findViewById(R.id.rlMain), "Username can't be empty...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getRespponseBody(new Dialog(mContext), retrofitApiClient.forgotPassword(strUsername), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        if (responseBody == null)
                            return;
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                ((EditText) findViewById(R.id.edtUsername)).setText("");
                                Alerts.show(findViewById(R.id.rlMain), jsonObject.getString("message"));
                            } else {
                                Alerts.show(findViewById(R.id.rlMain), jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
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
