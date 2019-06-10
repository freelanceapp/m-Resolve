package com.ibt.complaintmanagement.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.constant.Constant;
import com.ibt.complaintmanagement.modal.User;
import com.ibt.complaintmanagement.ui.fragment.ComplaintFragment;
import com.ibt.complaintmanagement.ui.fragment.HomeFragment;
import com.ibt.complaintmanagement.ui.fragment.StatusFragment;
import com.ibt.complaintmanagement.utils.AppPreference;
import com.ibt.complaintmanagement.utils.BaseActivity;
import com.ibt.complaintmanagement.utils.FragmentUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private View contentView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView txtTitle;
    public static FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNavigation();
    }

    private void initNavigation() {

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Home");

        findViewById(R.id.llHome).setOnClickListener(this);
        findViewById(R.id.llComplaint).setOnClickListener(this);
        findViewById(R.id.llStatus).setOnClickListener(this);
        findViewById(R.id.llLogout).setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        contentView = findViewById(R.id.container);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                final float xOffset = view.getWidth() * v;
                contentView.setTranslationX(xOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                contentView.animate().setDuration(300).translationX(0).translationY(0);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        };
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();

        ((TextView) findViewById(R.id.txtEmpName)).setText(User.getUser().getEMPNAME());
        ((TextView) findViewById(R.id.txtEmpId)).setText("Emp Id : " + User.getUser().getEMP());
        ((TextView) findViewById(R.id.txtEmpContact)).setText(User.getUser().getEmpContact());

        initFragment();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        ((ImageView) findViewById(R.id.imgHome)).setImageResource(R.drawable.ic_home_b);
        ((ImageView) findViewById(R.id.imgComplaint)).setImageResource(R.drawable.ic_complaint);
        ((ImageView) findViewById(R.id.imgStatus)).setImageResource(R.drawable.ic_status);

        ((TextView) findViewById(R.id.txtHome)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.txtComplaint)).setTextColor(getResources().getColor(R.color.text_color_a));
        ((TextView) findViewById(R.id.txtStatus)).setTextColor(getResources().getColor(R.color.text_color_a));

        drawerLayout.closeDrawer(GravityCompat.START);
        txtTitle.setText("Home");
        fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llHome:
                initFragment();
                break;
            case R.id.llComplaint:
                ((ImageView) findViewById(R.id.imgHome)).setImageResource(R.drawable.ic_home);
                ((ImageView) findViewById(R.id.imgComplaint)).setImageResource(R.drawable.ic_complaint_b);
                ((ImageView) findViewById(R.id.imgStatus)).setImageResource(R.drawable.ic_status);

                ((TextView) findViewById(R.id.txtHome)).setTextColor(getResources().getColor(R.color.text_color_a));
                ((TextView) findViewById(R.id.txtComplaint)).setTextColor(getResources().getColor(R.color.colorPrimary));
                ((TextView) findViewById(R.id.txtStatus)).setTextColor(getResources().getColor(R.color.text_color_a));

                drawerLayout.closeDrawer(GravityCompat.START);
                txtTitle.setText("File ComplaintList");
                fragmentUtils.replaceFragment(new ComplaintFragment(), Constant.ComplaintFragment, R.id.frameLayout);
                break;
            case R.id.llStatus:
                ((ImageView) findViewById(R.id.imgHome)).setImageResource(R.drawable.ic_home);
                ((ImageView) findViewById(R.id.imgComplaint)).setImageResource(R.drawable.ic_complaint);
                ((ImageView) findViewById(R.id.imgStatus)).setImageResource(R.drawable.ic_status_b);

                ((TextView) findViewById(R.id.txtHome)).setTextColor(getResources().getColor(R.color.text_color_a));
                ((TextView) findViewById(R.id.txtComplaint)).setTextColor(getResources().getColor(R.color.text_color_a));
                ((TextView) findViewById(R.id.txtStatus)).setTextColor(getResources().getColor(R.color.colorPrimary));

                drawerLayout.closeDrawer(GravityCompat.START);
                txtTitle.setText("Check Status");
                fragmentUtils.replaceFragment(new StatusFragment(), Constant.StatusFragment, R.id.frameLayout);
                break;
            case R.id.llLogout:
                logout();
                break;
        }
    }

    private void logout() {
        new AlertDialog.Builder(mContext)
                .setTitle("Logout")
                .setMessage("Are you sure want to logout ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppPreference.clearAllPreferences(mContext);
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .create()
                .show();
    }

}
