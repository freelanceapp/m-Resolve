package com.ibt.complaintmanagement.retrofit_provider;

import com.ibt.complaintmanagement.constant.Constant;
import com.ibt.complaintmanagement.modal.complaint_category.CategoryMainModal;
import com.ibt.complaintmanagement.modal.complaint_list.ComplaintMainModal;
import com.ibt.complaintmanagement.modal.user.UserMainModal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApiClient {

    @FormUrlEncoded
    @POST(Constant.LOGIN)
    Call<UserMainModal> userLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.FORGOT_PASSWORD)
    Call<ResponseBody> forgotPassword(@Field("username") String username);

    @GET(Constant.ALL_CATEGORY)
    Call<CategoryMainModal> allCategory();

    @GET(Constant.VERSION)
    Call<ResponseBody> appVersion();

    @FormUrlEncoded
    @POST(Constant.SUBMIT_COMPLAINT)
    Call<ResponseBody> submitComplaint(@Field("emp") String emp, @Field("emp_name") String emp_name,
                                       @Field("emp_contact") String emp_contact, @Field("comp_cat") String comp_cat,
                                       @Field("comp_cat_id") String comp_cat_id, @Field("comp_type") String comp_type,
                                       @Field("comp_type_id") String comp_type_id, @Field("comp_desc") String comp_desc);

    @FormUrlEncoded
    @POST(Constant.GET_COMPLAINT)
    Call<ComplaintMainModal> getComplaint(@Field("emp") String emp, @Field("page_number") String page_number,
                                          @Field("list_date") String list_date, @Field("call_type") String call_type);

    @FormUrlEncoded
    @POST(Constant.SUBMIT_FEEDBACK)
    Call<ResponseBody> submitFeedback(@Field("emp") String emp, @Field("comp_id") String comp_id,
                                      @Field("feedback") String feedback, @Field("comment") String comment);
}