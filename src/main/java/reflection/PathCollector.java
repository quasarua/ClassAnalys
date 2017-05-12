package reflection;

/**
 * Created by Quasar on 3/22/2017.
 */

        import java.io.File;
        import java.io.IOException;
        import java.nio.file.*;
        import java.nio.file.attribute.BasicFileAttributes;
        import java.util.*;


public class PathCollector {

    private static final PathMatcher CLASS_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.class");
    private static final PathMatcher JAR_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

    public static void collect(final Path parentPath, final Set<String> results) throws IOException {

        Files.walkFileTree(parentPath, new OnErrorFileVisitor() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (CLASS_MATCHER.matches(file)) {
                    Path rel = parentPath.relativize(file);
                    String pkg = rel
                            .toString()
                            .replaceAll(".class$", "")
                            .replaceAll("/", ".");
                    results.add(pkg);
                } else if (JAR_MATCHER.matches(file)) {
                    try (FileSystem jar = FileSystems.newFileSystem(file, null)) {
                        collect(jar.getPath("/"), results);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static class OnErrorFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            if (exc != null) {
                System.out.println("Exception on path: " + file);
                System.out.println(exc.getMessage());
                System.out.println(Arrays.toString(exc.getStackTrace()));
            }

            return FileVisitResult.CONTINUE;
        }
    }
}