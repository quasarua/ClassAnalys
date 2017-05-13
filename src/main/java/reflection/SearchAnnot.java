package reflection;

/**

 */

import reflection.annotations.Call;
import reflection.annotations.Handler;

import java.lang.reflect.*;

public class SearchAnnot {
    public static void printAnnotatedClass(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if (clazz.isAnnotationPresent(Handler.class)) {
            System.out.println("@Handler(name = " + clazz + " {");
            printAnnotatedMethods(clazz);
          //  invokeAnnotatedMethods(clazz);
            System.out.println("}");

        }
    }

    public static void printAnnotatedMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();

//        System.out.println("--public static void printAnnotatedMethods--");
//        System.out.println(cl.getName().toString());

        for (Method m : methods) {
            if (m.isAnnotationPresent(Call.class)) {
                Class retType = m.getReturnType();
                String name = m.getName();

                System.out.print(retType.getName() + " " + name + "(");

                // print parameter types
                Class[] paramTypes = m.getParameterTypes();
                for (int j = 0; j < paramTypes.length; j++) {
                    if (j > 0) {
                        System.out.print(", ");
                    }
                    System.out.print(paramTypes[j].getName());
                }
                System.out.println(");");
            }
        }

    }

    //how implements args list?
    public static void invokeAnnotatedMethods(Class cl) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Method method : cl.getMethods()) {
            if (method.isAnnotationPresent(Call.class)) {
                System.out.println("result exec method " + method.getName() + ": \n~~~~~~~~ ");
                method.invoke(cl.newInstance(), "!!!!!!!!!!!!!!!!!");
                System.out.println();
                System.out.println("~~~~~~~~ ");
            }
        }
    }
}
