package com.ibt.complaintmanagement.modal.complaint_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplaintList implements Parcelable {

    @SerializedName("LOCAL_DB_COMP_ID")
    @Expose
    private String lOCALDBCOMPID;
    @SerializedName("COMP_EMPNO")
    @Expose
    private String cOMPEMPNO;
    @SerializedName("COMP_EMPNAME")
    @Expose
    private String cOMPEMPNAME;
    @SerializedName("COMP_CONTACT_NO")
    @Expose
    private String cOMPCONTACTNO;
    @SerializedName("COMP_CAT")
    @Expose
    private String cOMPCAT;
    @SerializedName("COMP_TYPE")
    @Expose
    private String cOMPTYPE;
    @SerializedName("COMP_CAT_TITLE")
    @Expose
    private String cOMPCATTITLE;
    @SerializedName("COMP_TYPE_TITLE")
    @Expose
    private String cOMPTYPETITLE;
    @SerializedName("COMP_DESC")
    @Expose
    private String cOMPDESC;
    @SerializedName("COMP_DATE")
    @Expose
    private String cOMPDATE;
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("COMP_STATUS_DATE")
    @Expose
    private String cOMPSTATUSDATE;
    @SerializedName("COMP_ASSIGNED_TO_NAME")
    @Expose
    private String cOMPASSIGNEDTONAME;
    @SerializedName("COMP_DEPT_REMARKS")
    @Expose
    private String cOMPDEPTREMARKS;
    @SerializedName("COMP_ID")
    @Expose
    private String cOMPID;
    @SerializedName("COMP_FEEDBACK")
    @Expose
    private String cOMPFEEDBACK;
    @SerializedName("COMP_FEEDBACK_COMMENT")
    @Expose
    private String cOMPFEEDBACKCOMMENT;
    public final static Parcelable.Creator<ComplaintList> CREATOR = new Creator<ComplaintList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ComplaintList createFromParcel(Parcel in) {
            return new ComplaintList(in);
        }

        public ComplaintList[] newArray(int size) {
            return (new ComplaintList[size]);
        }

    };

    protected ComplaintList(Parcel in) {
        this.lOCALDBCOMPID = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPEMPNO = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPEMPNAME = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPCONTACTNO = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPCAT = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPTYPE = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPCATTITLE = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPTYPETITLE = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPDESC = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPDATE = ((String) in.readValue((String.class.getClassLoader())));
        this.sTATUS = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPSTATUSDATE = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPASSIGNEDTONAME = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPDEPTREMARKS = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPID = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPFEEDBACK = ((String) in.readValue((String.class.getClassLoader())));
        this.cOMPFEEDBACKCOMMENT = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ComplaintList() {
    }

    public String getLOCALDBCOMPID() {
        return lOCALDBCOMPID;
    }

    public void setLOCALDBCOMPID(String lOCALDBCOMPID) {
        this.lOCALDBCOMPID = lOCALDBCOMPID;
    }

    public String getCOMPEMPNO() {
        return cOMPEMPNO;
    }

    public void setCOMPEMPNO(String cOMPEMPNO) {
        this.cOMPEMPNO = cOMPEMPNO;
    }

    public String getCOMPEMPNAME() {
        return cOMPEMPNAME;
    }

    public void setCOMPEMPNAME(String cOMPEMPNAME) {
        this.cOMPEMPNAME = cOMPEMPNAME;
    }

    public String getCOMPCONTACTNO() {
        return cOMPCONTACTNO;
    }

    public void setCOMPCONTACTNO(String cOMPCONTACTNO) {
        this.cOMPCONTACTNO = cOMPCONTACTNO;
    }

    public String getCOMPCAT() {
        return cOMPCAT;
    }

    public void setCOMPCAT(String cOMPCAT) {
        this.cOMPCAT = cOMPCAT;
    }

    public String getCOMPTYPE() {
        return cOMPTYPE;
    }

    public void setCOMPTYPE(String cOMPTYPE) {
        this.cOMPTYPE = cOMPTYPE;
    }

    public String getCOMPCATTITLE() {
        return cOMPCATTITLE;
    }

    public void setCOMPCATTITLE(String cOMPCATTITLE) {
        this.cOMPCATTITLE = cOMPCATTITLE;
    }

    public String getCOMPTYPETITLE() {
        return cOMPTYPETITLE;
    }

    public void setCOMPTYPETITLE(String cOMPTYPETITLE) {
        this.cOMPTYPETITLE = cOMPTYPETITLE;
    }

    public String getCOMPDESC() {
        return cOMPDESC;
    }

    public void setCOMPDESC(String cOMPDESC) {
        this.cOMPDESC = cOMPDESC;
    }

    public String getCOMPDATE() {
        return cOMPDATE;
    }

    public void setCOMPDATE(String cOMPDATE) {
        this.cOMPDATE = cOMPDATE;
    }

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getCOMPSTATUSDATE() {
        return cOMPSTATUSDATE;
    }

    public void setCOMPSTATUSDATE(String cOMPSTATUSDATE) {
        this.cOMPSTATUSDATE = cOMPSTATUSDATE;
    }

    public String getCOMPASSIGNEDTONAME() {
        return cOMPASSIGNEDTONAME;
    }

    public void setCOMPASSIGNEDTONAME(String cOMPASSIGNEDTONAME) {
        this.cOMPASSIGNEDTONAME = cOMPASSIGNEDTONAME;
    }

    public String getCOMPDEPTREMARKS() {
        return cOMPDEPTREMARKS;
    }

    public void setCOMPDEPTREMARKS(String cOMPDEPTREMARKS) {
        this.cOMPDEPTREMARKS = cOMPDEPTREMARKS;
    }

    public String getCOMPID() {
        return cOMPID;
    }

    public void setCOMPID(String cOMPID) {
        this.cOMPID = cOMPID;
    }

    public String getCOMPFEEDBACK() {
        return cOMPFEEDBACK;
    }

    public void setCOMPFEEDBACK(String cOMPFEEDBACK) {
        this.cOMPFEEDBACK = cOMPFEEDBACK;
    }

    public String getCOMPFEEDBACKCOMMENT() {
        return cOMPFEEDBACKCOMMENT;
    }

    public void setCOMPFEEDBACKCOMMENT(String cOMPFEEDBACKCOMMENT) {
        this.cOMPFEEDBACKCOMMENT = cOMPFEEDBACKCOMMENT;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lOCALDBCOMPID);
        dest.writeValue(cOMPEMPNO);
        dest.writeValue(cOMPEMPNAME);
        dest.writeValue(cOMPCONTACTNO);
        dest.writeValue(cOMPCAT);
        dest.writeValue(cOMPTYPE);
        dest.writeValue(cOMPCATTITLE);
        dest.writeValue(cOMPTYPETITLE);
        dest.writeValue(cOMPDESC);
        dest.writeValue(cOMPDATE);
        dest.writeValue(sTATUS);
        dest.writeValue(cOMPSTATUSDATE);
        dest.writeValue(cOMPASSIGNEDTONAME);
        dest.writeValue(cOMPDEPTREMARKS);
        dest.writeValue(cOMPID);
        dest.writeValue(cOMPFEEDBACK);
        dest.writeValue(cOMPFEEDBACKCOMMENT);
    }

    public int describeContents() {
        return 0;
    }

}