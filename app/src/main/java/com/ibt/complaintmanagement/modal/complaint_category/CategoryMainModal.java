package com.ibt.complaintmanagement.modal.complaint_category;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("category")
    @Expose
    private List<MainCategory> category = new ArrayList<MainCategory>();
    public final static Parcelable.Creator<CategoryMainModal> CREATOR = new Creator<CategoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryMainModal createFromParcel(Parcel in) {
            return new CategoryMainModal(in);
        }

        public CategoryMainModal[] newArray(int size) {
            return (new CategoryMainModal[size]);
        }

    };

    protected CategoryMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.category, (MainCategory.class.getClassLoader()));
    }

    public CategoryMainModal() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MainCategory> getCategory() {
        return category;
    }

    public void setCategory(List<MainCategory> category) {
        this.category = category;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(category);
    }

    public int describeContents() {
        return 0;
    }

}