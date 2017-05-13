package reflection.test;

import reflection.annotations.Call;
import reflection.annotations.Handler;

/**

 */
@Handler(name = "MyTestClassWithMethods")
public class AnnotClass {

    @Call
    public void add(String s) {
        System.out.println("This is an add() method." + s);
    }

    private void del(String s) {
        System.out.println("This is an del() method." + s);
    }

    @Call
    public void rep(int i) {
        System.out.println("This is an replace() method." + i);
    }

    @Call
    public void replace_int_int(int i, int j) {
        System.out.println("This is an replace() method." + i + " j " + j);
    }

    @Call
    public void replace_float_int(float i, int j) {
        System.out.println("This is an replace() method." + i + " j " + j);
    }
}

