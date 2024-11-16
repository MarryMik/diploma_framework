package org.example.utils;

import org.example.models.DoctorInfo;
import org.example.models.PatientInfo;
import org.example.models.UserInfo;
import org.example.models.RecordInfo;
import org.example.models.RequestInfo;
import org.example.models.TreatmentInfo;


public interface IProductInfoReader {
    public DoctorInfo readDoctorInfo(String source);
    public PatientInfo readPatientInfo(String source);
    public UserInfo readUserInfo(String source);
    public RecordInfo readRecordInfo(String source);
    public RequestInfo readRequestInfo(String source);
    public TreatmentInfo readTreatmentInfo(String source);

}
