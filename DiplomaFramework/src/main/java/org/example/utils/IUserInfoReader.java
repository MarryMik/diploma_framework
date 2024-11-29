package org.example.utils;
import org.example.models.DoctorInfo;
import org.example.models.UserInfo;

public interface IUserInfoReader {
    public UserInfo readUserInfo(String source);
    public DoctorInfo readDoctorInfo(String source);

}
