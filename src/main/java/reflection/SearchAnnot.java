package reflection;

/**
 * Created by Quasar on 3/14/2017.
 */

import reflection.annotations.Call;
import reflection.annotations.Handler;
import reflection.test.TestClass;

import java.lang.reflect.*;
import java.util.*;


/*  receive List of annotated method */
public class SearchAnnot {
    public static Set<String> getAnnotatedClass(Set<String> clazzIn) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        ClassLoader cl = MainClass.class.getClassLoader();
        Set<String> annotClasses = new HashSet();
        for(String clazz : clazzIn) {
            //if (clazz.getClass().isAnnotationPresent(Handler.class)) {
            Class c = Class.forName(clazz);
            if (c.isAnnotationPresent(Handler.class)) {
                annotClasses.add(clazz);
            }
        }
        return annotClasses;
    } //end getAnnotatedClass


    /* receive Set<T> with class names and get Map<K, Set<V>> with  annotated methods */
    public static Map<String, Set<String>> getAnnotatedMethodsWithArgs(Set<String> cl) throws ClassNotFoundException {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();

        for (String strSet : cl) {
            Class c = Class.forName(strSet);
            Method[] methods = c.getDeclaredMethods();
            Set<String> annotMethodsInCl = new HashSet<String>();
            for (Method m : methods) {
                if (m.isAnnotationPresent(Call.class)) {
//                    System.out.println(Arrays.toString(m.getParameterTypes()));
                    annotMethodsInCl.add(m.getName());
                }
            }
            map.put(strSet,annotMethodsInCl);
        }
        return map;
    } // end getAnnotatedMethodsWithArgs




    //receive Map<String, Set<String>> = Map<ClassName, Set<ClassMethods>> and print in console invoke of them.
    public static void invokeAnnotatedMethods(Map<String, Set<String>> cl) throws IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        Map<String, Set<String>> res = new HashMap<String, Set<String>>();
        for (Map.Entry<String, Set<String>> entry : cl.entrySet()) {
            Class c = Class.forName(entry.getKey());
            Set<String> methods = new HashSet<String>(entry.getValue());
            for (String argsMethod: methods) {
//                System.out.println("class:" + entry.getKey() + " method: " + argsMethod);
                String str = "hello";
                Method testClMethod = c.getMethod(argsMethod, java.lang.String.class);
                Object t = c.newInstance();
                String re = (String) testClMethod.invoke(t, str);
            }
        }
    } //end invokeAnnotatedMethods

} //close class