package reflection.test;

import reflection.annotations.Call;
import reflection.annotations.Handler;

/**
 * Created by Quasar on 3/29/2017.
 */
@Handler(name = "MyTestClassWithMethods")
public class AnnotClass {

    @Call
    public String add(String s) {
        System.out.println("This is an add() method." + s);
        return s + " returned";
    }

    private void del(String s) {
        System.out.println("This is an del() method." + s);
    }

    @Call
    public void rep(String s) {
        System.out.println("This is an rep() method." + s);
    }

    @Call
    public void replace_int_int(String s) {
        System.out.println("This is an replace_int_int() method." + s);
    }

    @Call
    public void replace_str_int(String s) {
        System.out.println("This is an replace_str_int() method." + s);
    }


    @Call
    public void replace_float_int(String s) {
        System.out.println("This is an replace_float_int() method." + s);
    }
}

