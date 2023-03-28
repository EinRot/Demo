package tcp.selector.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NServer {
    private Selector selector = null;
    private Charset charset = Charset.forName("GBK");
    /**
     * ByteBuffer有几个重要概念:position、limit
     * ByteBuffer.allocate(1024) 开辟一个1024字节的空间
     * 当读取n个字节position就加n(从0开始)，limit 默认是开辟的空间最大的值
     * ByteBuffer.flip();重置position为0，limit为已用空间的最后一个值的索引(通常之后将是写数据的操作)
     * ByteBuffer.clear();重置position为0，limit为开辟空间的最大的值(通常之后是重新读取数据的操作)
     */
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private static final int PORT = 17785;

    public void init() throws IOException {
        //新建selector
        selector = Selector.open();
        //新建serversocket
        ServerSocketChannel server = ServerSocketChannel.open();
        //绑定本地端口
        server.socket().bind(new InetSocketAddress("127.0.0.1", PORT));
        //设置非阻塞模式
        server.configureBlocking(false);

        /**
         * 注册至soket服务器至selector
         * SelectionKey有4个状态：
         * OP_READ	可读模式
         * OP_WRITE	可写模式
         * OP_CONNECT	可连接模式
         * OP_ACCEPT	接受连接模式
         * 4个状态可以累加
         */
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            //遍历selector上的已注册的key并且处理key对应的channel
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sk = it.next();
                if (sk.isAcceptable()) {
                    //接受请求处理
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }
                if (sk.isReadable()) {
                    //读取客户端数据处理
                    SocketChannel sc = (SocketChannel) sk.channel();
                    String content = "";
                    try {
                        while (sc.read(buffer) > 0) {
                            //重置limit 为写数据做准备
                            buffer.flip();
                            content += charset.decode(buffer);
                            System.out.println("==================读取的数据是：" + content);
                            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sk.cancel();
                        if (sk.channel() != null)
                            sk.channel().close();
                    }
                    if (content.length() > 0) {
                        sk.attach(content);
                    }
                    //为读取数据做准备
                    buffer.clear();
                }
                if (sk.isValid() && sk.isWritable()) {
                    //写入客户端数据处理
                    SocketChannel sc = (SocketChannel) sk.channel();
                    String content = (String) sk.attachment();
                    sc.write(charset.encode(content));
                    sk.interestOps(SelectionKey.OP_READ);
                }
                //去除已经处理过的key
                it.remove();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NServer().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}