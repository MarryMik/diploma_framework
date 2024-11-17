package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.example.models.UserInfo;
import org.example.models.RecordInfo;
import org.example.models.RequestInfo;
import org.example.models.TreatmentInfo;

import java.io.File;
import java.io.IOException;

public class JsonFileReader implements IProductInfoReader{
    private static final Logger log = Logger.getLogger(JsonFileReader.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public UserInfo readUserInfo(String path){
        try{
            log.info("Read JSON file with test data. File path: " + path);
            File file = new File(path);
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

    public RecordInfo readRecordInfo(String path){
        try{
            log.info("Read JSON file with test data. File path: " + path);
            File file = new File(path);
            if(!file.exists() || file.isDirectory()){
                log.fatal("File doesn't exist or is not a directory: ");
                throw new IOException();
            }

            return mapper.readValue(file, RecordInfo.class);
        }catch(IOException e){
            log.fatal("Can`t find file. Error: " + e.getMessage());
        }
        return new RecordInfo();
    }

    public RequestInfo readRequestInfo(String path){
        try{
            log.info("Read JSON file with test data. File path: " + path);
            File file = new File(path);
            if(!file.exists() || file.isDirectory()){
                log.fatal("File doesn't exist or is not a directory: ");
                throw new IOException();
            }

            return mapper.readValue(file, RequestInfo.class);
        }catch(IOException e){
            log.fatal("Can`t find file. Error: " + e.getMessage());
        }
        return new RequestInfo();
    }

    public TreatmentInfo readTreatmentInfo(String path){
        try{
            log.info("Read JSON file with test data. File path: " + path);
            File file = new File(path);
            if(!file.exists() || file.isDirectory()){
                log.fatal("File doesn't exist or is not a directory: ");
                throw new IOException();
            }

            return mapper.readValue(file, TreatmentInfo.class);
        }catch(IOException e){
            log.fatal("Can`t find file. Error: " + e.getMessage());
        }
        return new TreatmentInfo();
    }
}
