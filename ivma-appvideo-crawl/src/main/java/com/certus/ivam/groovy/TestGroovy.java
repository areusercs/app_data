package com.certus.ivam.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import java.net.URL;

/**
 * Created by 123 on 2019/3/15.
 */
public class TestGroovy {
    public static void main(String[] args) {
        try {
            ClassLoader parent = TestGroovy.class.getClass().getClassLoader();
            GroovyClassLoader loader = new GroovyClassLoader(parent);
//            String scriptUrlPath = "https://github.com/userarecs/CheSh/blob/master/shell/pipixia_1_5_8_android.groovy";
            String scriptUrlPath = "https://github.com/userarecs/CheSh/blob/master/ivma-appvideo-crawl/dev/ivma-appvideo-crawl-dev.yml";
            String shellText = DefaultGroovyMethods.getText(new URL(scriptUrlPath), "utf-8");
            System.out.println(">>>"+shellText);
            Class<?> gclass = loader.parseClass(shellText);
            GroovyObject groovyObject = (GroovyObject) gclass.newInstance();
            groovyObject.invokeMethod("run", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
