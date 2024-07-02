package tcp.socket.server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO TCP服务启动入口
 * 　　* @date 2022/5/9
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class TcpApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TcpApplication.class);
        //不启动web服务
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
