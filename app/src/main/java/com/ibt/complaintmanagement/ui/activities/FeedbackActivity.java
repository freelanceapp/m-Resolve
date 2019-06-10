package com.ibt.complaintmanagement.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.modal.User;
import com.ibt.complaintmanagement.retrofit_provider.RetrofitService;
import com.ibt.complaintmanagement.retrofit_provider.WebResponse;
import com.ibt.complaintmanagement.utils.Alerts;
import com.ibt.complaintmanagement.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    private String strCompId = "";
    private float feedbackRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        init();
    }

    protected void init() {
        strCompId = getIntent().getStringExtra("comp_id");

        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.btnSubmit).setOnClickListener(this);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                feedbackRating = rating;
                String strRating = "";
                if (rating == 0) {
                    strRating = "";
                } else if (rating == 1) {
                    strRating = getResources().getString(R.string.complaint_not_resolved);
                } else if (rating == 2) {
                    strRating = getResources().getString(R.string.not_satisfied);
                } else if (rating == 3) {
                    strRating = getResources().getString(R.string.satisfied);
                } else if (rating == 4) {
                    strRating = getResources().getString(R.string.very_much_satisfied);
                }
                ((TextView) findViewById(R.id.txtComplaintTitle)).setText(strRating);
            }
        });
    }

    private void submitFeedbackApi() {
        String empId = User.getUser().getId();
        String strComment = ((EditText) findViewById(R.id.edtComment)).getText().toString();

        int intRating = (int) feedbackRating;

        if (strComment.isEmpty()) {
            Alerts.show(mContext, "Enter comment...!!!");
        } else if (feedbackRating == 0) {
            Alerts.show(mContext, "Please give rating...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getRespponseBody(new Dialog(mContext), retrofitApiClient.submitFeedback(empId, strCompId,
                        String.valueOf(intRating), strComment), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.getString("message"));
                                finish();
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
                        Alerts.show(mContext, error);
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnSubmit:
                submitFeedbackApi();
                break;
        }
    }
}
