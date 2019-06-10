package com.ibt.complaintmanagement.modal.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("EMP_NAME")
    @Expose
    private String eMPNAME;
    @SerializedName("EMP_CONTACT")
    @Expose
    private String empContact;
    @SerializedName("EMP")
    @Expose
    private String eMP;
    @SerializedName("EMP_CREATED_DATE")
    @Expose
    private String eMPCREATEDDATE;
    public final static Parcelable.Creator<UserData> CREATOR = new Creator<UserData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return (new UserData[size]);
        }

    };

    protected UserData(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.eMPNAME = ((String) in.readValue((String.class.getClassLoader())));
        this.empContact = ((String) in.readValue((String.class.getClassLoader())));
        this.eMP = ((String) in.readValue((String.class.getClassLoader())));
        this.eMPCREATEDDATE = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEMPNAME() {
        return eMPNAME;
    }

    public void setEMPNAME(String eMPNAME) {
        this.eMPNAME = eMPNAME;
    }

    public String getEmpContact() {
        return empContact;
    }

    public void setEmpContact(String empContact) {
        this.empContact = empContact;
    }

    public String getEMP() {
        return eMP;
    }

    public void setEMP(String eMP) {
        this.eMP = eMP;
    }

    public String getEMPCREATEDDATE() {
        return eMPCREATEDDATE;
    }

    public void setEMPCREATEDDATE(String eMPCREATEDDATE) {
        this.eMPCREATEDDATE = eMPCREATEDDATE;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(eMPNAME);
        dest.writeValue(empContact);
        dest.writeValue(eMP);
        dest.writeValue(eMPCREATEDDATE);
    }

    public int describeContents() {
        return 0;
    }

}