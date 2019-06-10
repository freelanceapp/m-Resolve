package com.ibt.complaintmanagement.modal.complaint_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ComplaintMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("complaint")
    @Expose
    private List<ComplaintList> complaint = new ArrayList<ComplaintList>();
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("current_page")
    @Expose
    private String currentPage;
    public final static Parcelable.Creator<ComplaintMainModal> CREATOR = new Creator<ComplaintMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ComplaintMainModal createFromParcel(Parcel in) {
            return new ComplaintMainModal(in);
        }

        public ComplaintMainModal[] newArray(int size) {
            return (new ComplaintMainModal[size]);
        }

    };

    protected ComplaintMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.complaint, (ComplaintList.class.getClassLoader()));
        this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currentPage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ComplaintMainModal() {
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

    public List<ComplaintList> getComplaint() {
        return complaint;
    }

    public void setComplaint(List<ComplaintList> complaint) {
        this.complaint = complaint;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(complaint);
        dest.writeValue(pageCount);
        dest.writeValue(currentPage);
    }

    public int describeContents() {
        return 0;
    }

}
