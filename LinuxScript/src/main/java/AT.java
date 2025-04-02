import com.fazecast.jSerialComm.SerialPort;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/8/15 14:23
 **/
@Slf4j
public class AT {
    public static void main(String[] args) throws IOException {
        // 查找可用的串口
        SerialPort[] ports = SerialPort.getCommPorts();

        // 打印所有可用的串口
        for (SerialPort serialPort : ports) {
            log.info("port:" + serialPort.getSystemPortName());
            if (serialPort.getSystemPortName().equals("ttyUSB2")) {
                // 打开串口
                if (serialPort.openPort()) {
                    // 设置波特率
                    serialPort.setBaudRate(115200); // 通常的波特率为115200

                    serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 5000, 0);
                    // 发送AT命令
                    String command = "AT+CGDCONT=1,\"IP\",\"xldny.grevpdn.js\"";
                    log.info("port: {} command: {}", serialPort.getSystemPortName(), command);
                    serialPort.getOutputStream().write(command.getBytes());
                    serialPort.getOutputStream().write('\r');

                    String dial = "ATD*99#";
                    serialPort.getOutputStream().write(dial.getBytes());
                    serialPort.getOutputStream().write('\r');

                    // 读取响应
                    byte[] buffer = new byte[1024];
                    int numRead;
                    while ((numRead = serialPort.getInputStream().read(buffer)) > 0) {
                        String response = new String(buffer, 0, numRead);
                        log.info("response: " + response);
                        // 如果接收到"OK"则认为命令执行成功
                        if (response.contains("OK")) {
                            break;
                        }
                    }
                    // 关闭串口
                    serialPort.closePort();
                } else {
                    log.info("Failed to open the serial port.");
                }
            }
        }
    }
}
