package com.ibt.complaintmanagement.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.modal.User;
import com.ibt.complaintmanagement.pagination_listener.PasswordUpdateListener;
import com.ibt.complaintmanagement.retrofit_provider.RetrofitService;
import com.ibt.complaintmanagement.retrofit_provider.WebResponse;
import com.ibt.complaintmanagement.utils.Alerts;
import com.ibt.complaintmanagement.utils.BaseFragment;
import com.ibt.complaintmanagement.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SetPasswordFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private PasswordUpdateListener updateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_set_password, container, false);
        mContext = getActivity();
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    public void initListener(PasswordUpdateListener passwordUpdateListener) {
        this.updateListener = passwordUpdateListener;
    }

    protected void init() {
        rootView.findViewById(R.id.btnSubmit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                changePasswordApi();
                break;
        }
    }

    private void changePasswordApi() {
        String empId = User.getUser().getId();
        String strOld = ((EditText) rootView.findViewById(R.id.edtOldPass)).getText().toString();
        String strNewPass = ((EditText) rootView.findViewById(R.id.edtNewPass)).getText().toString();
        String strConfirmPass = ((EditText) rootView.findViewById(R.id.edtConfirmPass)).getText().toString();

        if (strOld.isEmpty()) {
            Alerts.show(mContext, "Enter OLD Password first...!!!");
        } else if (strNewPass.isEmpty()) {
            Alerts.show(mContext, "Enter New Password...!!!");
        } else if (strNewPass.length() < 6) {
            Alerts.show(mContext, "New Password length must be more than 5...!!!");
        } else if (strConfirmPass.isEmpty()) {
            Alerts.show(mContext, "Enter Confirm Password...!!!");
        } else if (!strConfirmPass.equals(strNewPass)) {
            Alerts.show(mContext, "Password does not match...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getRespponseBody(new Dialog(mContext), retrofitApiClient.changePassword(
                        empId, strOld, strNewPass), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.getString("message"));
                                updateListener.onSuccess(true);
                            } else {
                                Alerts.show(mContext, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, "Server error...!!!");
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }
}
