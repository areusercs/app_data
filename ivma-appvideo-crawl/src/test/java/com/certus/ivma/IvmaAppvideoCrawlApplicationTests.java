package com.certus.ivma;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IvmaAppvideoCrawlApplicationTests {

	@Test
	public void contextLoads() {
	}

	public void testGroovy() {

		try {
			ClassLoader parent = this.getClass().getClassLoader();
			GroovyClassLoader loader = new GroovyClassLoader(parent);
			String scriptUrlPath = "https://github.com/userarecs/CheSh/blob/master/shell/pipixia_1_5_8_android.groovy";
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
