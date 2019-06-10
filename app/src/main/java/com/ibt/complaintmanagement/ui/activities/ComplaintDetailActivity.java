package com.ibt.complaintmanagement.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.modal.complaint_list.ComplaintList;
import com.ibt.complaintmanagement.utils.BaseActivity;

public class ComplaintDetailActivity extends BaseActivity implements View.OnClickListener {

    private ComplaintList complaint;
    private String[] status = new String[]{"Pending", "Accepted", "Resolved", "Under Process", "Rejected"};
    private int[] drawable = {R.drawable.ic_status_c, R.drawable.ic_status_c, R.drawable.ic_resloved,
            R.drawable.ic_status_c, R.drawable.ic_status_c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_detail);

        init();
    }

    protected void init() {
        findViewById(R.id.btnFeedback).setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(this);
        findViewById(R.id.btnFeedback).setOnClickListener(this);

        complaint = getIntent().getParcelableExtra("data");
        setData();
    }

    private void setData() {
        ((TextView) findViewById(R.id.txtComplaintId)).setText(complaint.getCOMPID());
        ((TextView) findViewById(R.id.txtComplaintDate)).setText(complaint.getCOMPDATE());
        ((TextView) findViewById(R.id.txtComplaintCategory)).setText(complaint.getCOMPCATTITLE());
        ((TextView) findViewById(R.id.txtComplaintSubCategory)).setText(complaint.getCOMPTYPETITLE());
        ((TextView) findViewById(R.id.txtComplaintDetail)).setText(complaint.getCOMPDESC());
        ((TextView) findViewById(R.id.txtComplaintContact)).setText(complaint.getCOMPCONTACTNO());

        String strS = complaint.getSTATUS();
        if (strS.isEmpty()) {
            strS = "0";
        }
        int statusPos = Integer.parseInt(strS);
        ((TextView) findViewById(R.id.txtComplaintStatus)).setText(status[statusPos]);
        ((TextView) findViewById(R.id.txtComplaintStatus)).setCompoundDrawablesWithIntrinsicBounds(0,
                0, drawable[statusPos], 0);

        ((TextView) findViewById(R.id.txtStatusDate)).setText(complaint.getCOMPSTATUSDATE());
        ((TextView) findViewById(R.id.txtAssignedTo)).setText(complaint.getCOMPASSIGNEDTONAME());

        if (strS.equalsIgnoreCase("2")) {
            findViewById(R.id.btnBack).setEnabled(false);
            findViewById(R.id.btnFeedback).setEnabled(true);
            findViewById(R.id.btnBack).setBackground(getResources().getDrawable(R.drawable.button_trans_blue));
            findViewById(R.id.btnFeedback).setBackground(getResources().getDrawable(R.drawable.button_ripple_b));
        } else {
            findViewById(R.id.btnBack).setEnabled(true);
            findViewById(R.id.btnFeedback).setEnabled(false);
            findViewById(R.id.btnBack).setBackground(getResources().getDrawable(R.drawable.button_ripple_a));
            findViewById(R.id.btnFeedback).setBackground(getResources().getDrawable(R.drawable.button_trans_orange));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFeedback:
                String complainId = complaint.getCOMPID();
                Intent i = new Intent(mContext, FeedbackActivity.class);
                i.putExtra("comp_id", complainId);
                startActivity(i);
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
