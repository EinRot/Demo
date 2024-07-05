import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author 小工匠
 * @version 1.0
 * @mark: show me the code , change the world
 */
public class CompileFile2 {

    public static void main(String[] args) throws Exception {

        // 获取系统Java编译器
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        // 获取标准文件管理器
        StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);
        // 获取要编译的文件对象
        Iterable fileObjects = fileManager.getJavaFileObjects("D:\\Project\\Idea\\EinIce\\GitHub\\Demo\\MavenBuild\\DynamicCompiler\\src\\main\\java\\HelloWorld.java");
        // 创建编译任务
        JavaCompiler.CompilationTask cTask   = jc.getTask(null, fileManager, null, null, null, fileObjects);
        // 执行编译任务
        cTask.call();
        // 关闭文件管理器
        fileManager.close();

        // 使用URLClassLoader加载class到内存
        URL[] urls = new URL[]{new URL("file:/src/main/java/")};
        URLClassLoader cLoader = new URLClassLoader(urls);
        // 加载类
        Class c = cLoader.loadClass("HelloWorld");
        //方法参数
        Method method = c.getDeclaredMethod("main", String[].class);
        method.invoke(null, (Object) new String[] {});
        // 关闭类加载器
        cLoader.close();
    }
}