package org.example.xbidetection;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Screenshot {
    private final String path;
    private final String pageName;
    private final String browserName;

    public Screenshot(String path, String pageName, String browserName) {
        this.path = path;
        this.pageName = pageName;
        this.browserName = browserName;
    }

    public String getPath() {
        return path;
    }

    public String getPageName() {
        return pageName;
    }

    public String getBrowserName() {
        return browserName;
    }

    private File processContrast(File file) {
        return new File(path);
    }

    private File processBrightness(File file){ return new File(path); }

    private File processNoises(File file){ return new File(path); }

    public List<Double> detectCBD(String filepath){
        return Arrays.asList(23.8, 0.0);
    }

    public Screenshot processScreenshot(){
        File afterContrast = processContrast(new File(path));
        File afterBrightness = processBrightness(afterContrast);
        File afterNoises = processNoises(afterBrightness);
        return new Screenshot(afterNoises.getPath(), pageName, browserName);
    }

}
