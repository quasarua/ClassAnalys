package reflection;

/**
 * Created by Quasar on 3/22/2017.
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MainClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        String cpElements = System.getProperty("java.class.path");

        Set<String> loadedClasses = ExecContainer.ExecContainer(PathCollect.coll(cpElements));

        Set<String> annotClasses = SearchAnnot.getAnnotatedClass(loadedClasses);

        Map<String, Set<String>> annotMethodsAndClass = SearchAnnot.getAnnotatedMethodsWithArgs(annotClasses);


        System.out.println("~~~~!$!$!$!$~~~~~~ ");
        for (Map.Entry<String, Set<String>> entry : annotMethodsAndClass.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
        System.out.println("~~~~!$!$!$!$~~~~~~ ");

        SearchAnnot.invokeAnnotatedMethods(annotMethodsAndClass);
    }
}