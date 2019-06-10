package com.ibt.complaintmanagement.modal.complaint_category;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCategory implements Parcelable {

    @SerializedName("COMP_CAT_ID")
    @Expose
    private String cOMPCATID;
    @SerializedName("COMP_CAT_NAME")
    @Expose
    private String cOMPCATNAME;
    @SerializedName("type")
    @Expose
    private List<SubCategory> type = new ArrayList<SubCategory>();
    public final static Parcelable.Creator<MainCategory> CREATOR = new Creator<MainCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MainCategory createFromParcel(Parcel in) {
            return new MainCategory(in);
        }

        public MainCategory[] newArray(int size) {
            return (new MainCategory[size]);
        }

    };

    protected MainCategory(Parcel in) {
        this.cOMPCATID = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPCATNAME = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.type, (SubCategory.class.getClassLoader()));
    }

    public MainCategory() {
    }

    public String getCOMPCATID() {
        return cOMPCATID;
    }

    public void setCOMPCATID(String cOMPCATID) {
        this.cOMPCATID = cOMPCATID;
    }

    public String getCOMPCATNAME() {
        return cOMPCATNAME;
    }

    public void setCOMPCATNAME(String cOMPCATNAME) {
        this.cOMPCATNAME = cOMPCATNAME;
    }

    public List<SubCategory> getType() {
        return type;
    }

    public void setType(List<SubCategory> type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cOMPCATID);
        dest.writeValue(cOMPCATNAME);
        dest.writeList(type);
    }

    public int describeContents() {
        return 0;
    }

}