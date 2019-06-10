package com.ibt.complaintmanagement.modal.complaint_category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategory implements Parcelable {

    @SerializedName("COMP_TYPE_ID")
    @Expose
    private String cOMPTYPEID;
    @SerializedName("COMP_TYPE")
    @Expose
    private String cOMPTYPE;
    public final static Parcelable.Creator<SubCategory> CREATOR = new Creator<SubCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }

        public SubCategory[] newArray(int size) {
            return (new SubCategory[size]);
        }

    };

    protected SubCategory(Parcel in) {
        this.cOMPTYPEID = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPTYPE = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SubCategory() {
    }

    public String getCOMPTYPEID() {
        return cOMPTYPEID;
    }

    public void setCOMPTYPEID(String cOMPTYPEID) {
        this.cOMPTYPEID = cOMPTYPEID;
    }

    public String getCOMPTYPE() {
        return cOMPTYPE;
    }

    public void setCOMPTYPE(String cOMPTYPE) {
        this.cOMPTYPE = cOMPTYPE;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cOMPTYPEID);
        dest.writeValue(cOMPTYPE);
    }

    public int describeContents() {
        return 0;
    }

}
