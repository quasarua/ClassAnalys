package reflection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Quasar on 5/29/2017.
 */

/* return list with loaded files name */
public class ExecContainer {
    public static Set<String> ExecContainer(Set<String> paths){
        ClassLoader cl = MainClass.class.getClassLoader();

        Set <String> loadedClasses = new HashSet<>();

        for(String clazz : paths)
            try {
                Class someClass = cl.loadClass(clazz);
                loadedClasses.add(clazz);
//                SearchAnnot.printAnnotatedClass(someClass);
//                SearchAnnot.printAnnotatedMethods(someClass);
            } catch (Throwable ex) { //ERROR is possible
                System.out.println("!#!#!# Error class " + cl.getClass().getName() + " not loaded !#!#!#");
//                ex.printStackTrace();
            }

        return loadedClasses;
    }


}
