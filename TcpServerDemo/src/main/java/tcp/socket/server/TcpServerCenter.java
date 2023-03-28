package tcp.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO TCP服务中心
 * 　　* @date 2022/5/10
 */
public class TcpServerCenter{
    // 默认服务器端口
    private int port = 17785;
    private Map<String,SocketThread> socketThreads= new HashMap<>();

    private ServerSocket serverSocket;

//    private
    public TcpServerCenter() {
    }

    public TcpServerCenter(int port) {
        this.port = port;
    }

    // 提供服务
    public void initTcpServer(){
        try {
            //建立服务器连接
            serverSocket = new ServerSocket(port);
            System.out.println("TCP服务端启动成功,端口:"+port);
            //等待客户连接
            while (true) {
                Socket socket = serverSocket.accept();//接受连接
                socket.setKeepAlive(true);
                socket.setSoTimeout(5 * 60 * 1000);
                String address = socket.getInetAddress().getHostAddress()+":"+socket.getPort();
                System.out.println("建立连接:"+address);
                //创建线程处理连接
                SocketThread socketThread = new SocketThread(socket,socketThreads);
                socketThreads.put(address,socketThread);
                socketThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void off(){
        System.out.println("关闭服务端");
        try {
            socketThreads.forEach((address,socketThread)->{
                socketThread.close();
            });
            serverSocket.close();
            System.out.println("剩余连接数："+socketThreads.size());
//            socketThreads.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
      * @description TODO TCP服务main启动入口
      */
    public static void main(String[] args){
        TcpServerCenter tcpServerCenter = new TcpServerCenter();
        new Thread(tcpServerCenter::initTcpServer).start();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            if (str.equals("1")) {
                System.out.println("用户强制终止程序!");
                tcpServerCenter.off();
                break;
            }
        }
    }
}
