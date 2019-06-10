package com.ibt.complaintmanagement.modal.service_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceList implements Parcelable {

    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("service_title")
    @Expose
    private String serviceTitle;
    @SerializedName("service_description")
    @Expose
    private String serviceDescription;
    @SerializedName("service_image")
    @Expose
    private String serviceImage;
    public final static Creator<ServiceList> CREATOR = new Creator<ServiceList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceList createFromParcel(Parcel in) {
            return new ServiceList(in);
        }

        public ServiceList[] newArray(int size) {
            return (new ServiceList[size]);
        }

    };

    protected ServiceList(Parcel in) {
        this.serviceId = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ServiceList() {
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(serviceId);
        dest.writeValue(serviceTitle);
        dest.writeValue(serviceDescription);
        dest.writeValue(serviceImage);
    }

    public int describeContents() {
        return 0;
    }

}