package tcp.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO socket连接处理线程
 * 　　* @date 2022/5/10
 */
public class SocketThread extends Thread {
    private String address;
    private Socket socket;

    private InputStream in;
    private OutputStream out;

    private Map<String, SocketThread> socketThreads;

    public SocketThread(Socket socket, Map socketThreads) {
        this.socket = socket;
        this.socketThreads = socketThreads;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            address = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接初始化失败！");
        }
    }

    @Override
    public void run() {
        if (in == null) {
            System.out.println(address + ":输入流为空!");
            return;
        }
        try {
            System.out.println("开始接收消息!");
            //————————————————————读取十六进制————————————————————
            int lenHex;
            byte[] bysHex = new byte[1024];
            while ((lenHex = in.read(bysHex)) != -1) {
                String data = BinaryToHexString(bysHex,lenHex);
                System.out.println(data);
            }
            //————————————————————读取字符串————————————————————
//            int len;
//            byte[] bys = new byte[1024];
//            while ((len = in.read(bys)) != -1) {
//                String data = new String(bys, 0, len);
//                jinXunDataCotPces.insertCollectData(data);
//            }
            //————————————————————————————————————————————————
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据读取错误！");
        }
        try {
            socketThreads.remove(address);
            in.close();
            out.close();
            socket.close();
            System.out.println("设备断开:" + address);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接关闭失败！");
        }
    }

    /**
     * @description TODO 将字节数组转换成十六进制的字符串
     */
    public String BinaryToHexString(byte[] bytes,int len) {
        String hexStr = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder();
        for (int i = 0;i<len;i++) {
            result.append(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            result.append(hexStr.charAt(bytes[i] & 0x0F));
            if(i!=(len-1))result.append(" ");
        }
        return result.toString();
    }

    public Socket getSocket() {
        return socket;
    }

    public void close() {
        try {
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("设备关闭失败:" + address);
        }
    }
}
