package reflection;

/**
 * Created by Quasar on 3/22/2017.
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Set<String> paths = new HashSet<>();
        String[] classPathElements = System
                .getProperty("java.class.path")
                .split(System.getProperty("path.separator"));

        for(String element : classPathElements) {
            PathCollector.collect(Paths.get(element), paths);
        }

        ClassLoader cl = MainClass.class.getClassLoader();

        for(String clazz : paths)
            try {
                Class someClass = cl.loadClass(clazz);
                SearchAnnot.printAnnotatedClass(someClass);
//                SearchAnnot.printAnnotatedMethods(someClass);
            } catch (Throwable ex) { //ERROR is possible
                System.out.println("!#!#!# Error class " + cl.getClass().getName() + " not loaded !#!#!#");
//                ex.printStackTrace();
            }
    }
}