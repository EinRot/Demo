import javax.tools.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
 
public class CompileFile {
    public static void main(String[] args) throws Exception {
        String source = 
            "public class HelloWorld {\n" +
            "    public static void main(String[] args) {\n" +
            "        System.out.println(\"Hello, World!\");\n" +
            "    }\n" +
            "}";
 
        // 编译源代码
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileObject file = new JavaSourceFromString("HelloWorld", source);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
        Boolean result = task.call();
        fileManager.close();
 
        // 如果编译成功，使用自定义ClassLoader加载并运行编译后的类
        if (result) {
            URL[] urls = new URL[] {new URL("file:/" + System.getProperty("user.dir"))};
            URLClassLoader cl = new URLClassLoader(urls);
            Class<?> cls = cl.loadClass("HelloWorld");
            Method method = cls.getDeclaredMethod("main", String[].class);
            method.invoke(null, (Object) new String[] {});
        }
    }
 
    private static class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;
 
        JavaSourceFromString(String name, String code) {
            super(createUri(name), Kind.SOURCE);
            this.code = code;
        }
 
        private static URI createUri(String name) {
            try {
                return new URI(name + Kind.SOURCE.extension);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
 
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
}