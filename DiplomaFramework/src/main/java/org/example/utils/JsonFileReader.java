package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.example.models.DoctorInfo;
import org.example.models.UserInfo;

import java.io.File;
import java.io.IOException;

public class JsonFileReader implements IUserInfoReader{
    private static final Logger log = Logger.getLogger(JsonFileReader.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public UserInfo readUserInfo(String path){
        try{
            log.info("Read JSON file with test data. File path: " + path);
            File file = new File(path);
            log.info(path);
            if(!file.exists() || file.isDirectory()){
                log.fatal("File doesn't exist or is not a directory: ");
                throw new IOException();
            }

            return mapper.readValue(file, UserInfo.class);
        }catch(IOException e){
            log.fatal("Can`t find file. Error: " + e.getMessage());
        }
        return new UserInfo();
    }

    public DoctorInfo readDoctorInfo(String path){
        try{
            log.info("Read JSON file with test data. File path: " + path);
            File file = new File(path);
            log.info(path);
            if(!file.exists() || file.isDirectory()){
                log.fatal("File doesn't exist or is not a directory: ");
                throw new IOException();
            }

            return mapper.readValue(file, DoctorInfo.class);
        }catch(IOException e){
            log.fatal("Can`t find file. Error: " + e.getMessage());
        }
        return new DoctorInfo();
    }

}
