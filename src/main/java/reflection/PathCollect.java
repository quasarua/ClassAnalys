package reflection;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Quasar on 5/29/2017.
 */
public class PathCollect {
    public static Set coll(String cpElements) throws IOException {

        Set<String> paths = new HashSet<>();
        String[] classPathElements = System
                .getProperty("java.class.path")
                .split(System.getProperty("path.separator"));

        for (String element : classPathElements) {
            PathCollector.collect(Paths.get(element), paths);
        }

        return paths;

    }
}
