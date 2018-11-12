package aop.main;

import aop.objects.FileManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        FileManager fileManager = (FileManager) context.getBean("fileManager");
        fileManager.getExtensionsList("c:\\windows\\system32");
        fileManager.getExtensionsCount("c:\\windows");

    }
}
