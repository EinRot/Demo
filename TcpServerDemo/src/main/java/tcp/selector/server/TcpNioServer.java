package tcp.selector.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 非阻塞TCP服务端
 * 　　* @date 2022/5/13
 *
 */
public class TcpNioServer {
    public static void main(String[] args) throws IOException {
        try {
            //创建ServerSocket通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //ServerSocket监听8000端口
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 17785));
            //设置通道为非阻塞  必须的
            ssc.configureBlocking(false);
            //创建一个选择器
            Selector selector = Selector.open();
            // 注册 channel，并且指定感兴趣的事件是 Accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();
            while (true) {
                //主线程阻塞在`selector`的`select`方法中
                //当选择器发现`socket`就绪了，某个socket就绪了。就会唤醒主线程，
                //然后咱们可以通过`selector` 获取就绪状态的`socket` 进行相应的处理。
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                //需要遍历一次
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        // 创建新的连接，并且把连接注册到selector上，而且，
                        // 声明这个channel只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    //可读
                    else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();
                        System.out.println("received : " + new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    //可写
                    else if (key.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
