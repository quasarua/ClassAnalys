package reflection.test;

import reflection.annotations.Call;
import reflection.annotations.Handler;

/**
 * Created by Quasar on 3/11/2017.
 */
@Handler(name = "Class1")
public class TestClass {

    @Call
    public void myPublicTestMethod(String s) {
        System.out.println("YES it IS an ANNOTATED method " + s);
    }

    public void badRobot(String s) {
        System.out.println("Method IS NOT ANNOTATED " + s);
    }
}
