import javax.tools.*;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/4 11:48
 **/
public class LoadManager {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\Project\\Idea\\EinIce\\GitHub\\Demo\\MavenBuild\\DynamicCompiler\\src\\main\\java\\HelloWorld.java");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { // 读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
//        String code = buffer.toString();
        String code = "public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello World!\"); } }";


        // 创建 JavaCompiler 对象
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 创建 DiagnosticCollector 对象，用于收集编译时的诊断信息
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        // 创建 JavaFileManager 对象，用于管理编译过程中的文件
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        // 创建 JavaFileObject 对象，用于表示要编译的 Java 源代码
        JavaFileObject source = new JavaSourceFromString("HelloWorld", code);
        // 获取 CompilationTask 对象
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(source);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
        // 编译 Java 源代码
        boolean success = task.call();
        // 获取诊断信息
        List<Diagnostic<? extends JavaFileObject>> messages = diagnostics.getDiagnostics();
        for (Diagnostic<? extends JavaFileObject> message : messages) {
            System.out.println(message.getMessage(null));
        }
        // 处理编译结果
        if (success) {
            System.out.println("Compilation was successful.");
        } else {
            System.out.println("Compilation failed.");
        }
        fileManager.close();

    }
}
