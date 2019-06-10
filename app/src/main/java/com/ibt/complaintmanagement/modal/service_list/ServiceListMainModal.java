package com.ibt.complaintmanagement.modal.service_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ServiceListMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("services")
    @Expose
    private List<ServiceList> services = new ArrayList<ServiceList>();
    public final static Creator<ServiceListMainModal> CREATOR = new Creator<ServiceListMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceListMainModal createFromParcel(Parcel in) {
            return new ServiceListMainModal(in);
        }

        public ServiceListMainModal[] newArray(int size) {
            return (new ServiceListMainModal[size]);
        }

    };

    protected ServiceListMainModal(Parcel in) {
        this.result = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.services, (ServiceList.class.getClassLoader()));
    }

    public ServiceListMainModal() {
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ServiceList> getServices() {
        return services;
    }

    public void setServices(List<ServiceList> services) {
        this.services = services;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(result);
        dest.writeValue(message);
        dest.writeList(services);
    }

    public int describeContents() {
        return 0;
    }

}
