package com.ibt.complaintmanagement.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.adapter.SpinnerCategoryAdapter;
import com.ibt.complaintmanagement.adapter.SpinnerSubCategoryAdapter;
import com.ibt.complaintmanagement.modal.User;
import com.ibt.complaintmanagement.modal.complaint_category.CategoryMainModal;
import com.ibt.complaintmanagement.modal.complaint_category.MainCategory;
import com.ibt.complaintmanagement.modal.complaint_category.SubCategory;
import com.ibt.complaintmanagement.retrofit_provider.RetrofitService;
import com.ibt.complaintmanagement.retrofit_provider.WebResponse;
import com.ibt.complaintmanagement.utils.Alerts;
import com.ibt.complaintmanagement.utils.BaseFragment;
import com.ibt.complaintmanagement.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

import static android.R.layout.simple_spinner_dropdown_item;

public class ComplaintFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private SpinnerCategoryAdapter categoryAdapter;
    private SpinnerSubCategoryAdapter subCategoryAdapter;
    private List<MainCategory> mainCategoryList = new ArrayList<>();
    private List<SubCategory> subCategoryList = new ArrayList<>();

    private String mainCatId = "", mainCatName = "", subCatId = "", subCatName = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_file_complaint, container, false);
        mContext = getActivity();
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    private void init() {
        rootView.findViewById(R.id.btnSubmit).setOnClickListener(this);
        ((EditText) rootView.findViewById(R.id.edtContact)).setText(User.getUser().getEmpContact());

        Spinner spinnerSubCategory = rootView.findViewById(R.id.spinnerSubCategory);
        subCategoryAdapter = new SpinnerSubCategoryAdapter(mContext, R.layout.spinner_category_list, subCategoryList);
        subCategoryAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinnerSubCategory.setAdapter(subCategoryAdapter);

        Spinner spinnerCategory = rootView.findViewById(R.id.spinnerCategory);
        categoryAdapter = new SpinnerCategoryAdapter(mContext, R.layout.spinner_category_list, mainCategoryList);
        categoryAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subCategoryList.clear();
                if (mainCategoryList.size() > 0) {
                    mainCatId = mainCategoryList.get(position).getCOMPCATID();
                    mainCatName = mainCategoryList.get(position).getCOMPCATNAME();
                    if (mainCategoryList.get(position).getType().size() > 0) {
                        rootView.findViewById(R.id.llSubSpinner).setVisibility(View.VISIBLE);
                        subCategoryList.addAll(mainCategoryList.get(position).getType());
                    } else {
                        rootView.findViewById(R.id.llSubSpinner).setVisibility(View.GONE);
                    }
                }
                subCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (subCategoryList.size() > 0) {
                    subCatId = subCategoryList.get(position).getCOMPTYPEID();
                    subCatName = subCategoryList.get(position).getCOMPTYPE();
                } else {
                    subCatId = "0";
                    subCatName = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        categoryApi();
    }

    private void categoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getCategoryList(new Dialog(mContext), retrofitApiClient.allCategory(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    CategoryMainModal mainModal = (CategoryMainModal) result.body();
                    if (mainModal == null)
                        return;
                    mainCategoryList.clear();
                    if (!mainModal.getError()) {
                        if (mainModal.getCategory().size() > 0) {
                            mainCategoryList.addAll(mainModal.getCategory());
                        }
                    } else {
                        Alerts.show(rootView.findViewById(R.id.llMain), mainModal.getMessage());
                    }
                    categoryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(rootView.findViewById(R.id.llMain), "Server error...!!!");
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                submitComplaintApi();
                break;
        }
    }

    private void submitComplaintApi() {
        String empId = User.getUser().getId();
        String empName = User.getUser().getEMP();
        String strContact = ((EditText) rootView.findViewById(R.id.edtContact)).getText().toString();
        String strDescription = ((EditText) rootView.findViewById(R.id.edtDescription)).getText().toString();

        if (strDescription.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llMain), "Description can't be empty...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getRespponseBody(new Dialog(mContext), retrofitApiClient.submitComplaint(empId,
                        empName, strContact, mainCatName, mainCatId, subCatName, subCatId, strDescription), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        if (responseBody == null)
                            return;
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                ((EditText) rootView.findViewById(R.id.edtContact)).setText("");
                                ((EditText) rootView.findViewById(R.id.edtDescription)).setText("");
                                Alerts.show(rootView.findViewById(R.id.llMain), jsonObject.getString("message"));
                            } else {
                                Alerts.show(rootView.findViewById(R.id.llMain), jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(rootView.findViewById(R.id.llMain), "Server error");
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }
}
