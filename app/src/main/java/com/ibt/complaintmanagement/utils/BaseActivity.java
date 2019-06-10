package com.ibt.complaintmanagement.utils;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ibt.complaintmanagement.retrofit_provider.RetrofitApiClient;
import com.ibt.complaintmanagement.retrofit_provider.RetrofitService;

public class BaseActivity extends AppCompatActivity {

    public Context mContext;
    public RetrofitApiClient retrofitApiClient;
    public ConnectionDetector cd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
    }
}
