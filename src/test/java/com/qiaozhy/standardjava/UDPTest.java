package com.qiaozhy.standardjava;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/7/4 8:12 PM
 */
public class UDPTest {
    @Test
    public  void main() throws IOException
    {
        DatagramSocket server = new DatagramSocket(9999);
        DatagramPacket recvPacket = new DatagramPacket(new byte[255], 255);

        System.out.println("waitting");
        server.receive(recvPacket);
        System.out.println("start connecting!");
        byte[] receiveMsg = Arrays.copyOfRange(recvPacket.getData(),
                recvPacket.getOffset(),
                recvPacket.getOffset() + recvPacket.getLength());
        System.out.println("Receive Data:" + new String(receiveMsg));
        server.send(recvPacket);
    }
    @Test
    public  void main2() throws IOException
    {
        byte[] msg = new String("connect test successfully!!!").getBytes();
        DatagramSocket client = new DatagramSocket();
        //获取本机ip信息
        InetAddress inetAddr = InetAddress.getLocalHost();
        SocketAddress socketAddr = new InetSocketAddress(inetAddr, 9999);
        DatagramPacket sendPacket = new DatagramPacket(msg, msg.length, socketAddr);
        client.send(sendPacket);
        client.close();
    }
    @Test
    public void main3() throws IOException
    {
        InetAddress inetRemoteAddr = InetAddress.getByName("224.0.0.5");
        DatagramPacket recvPack = new DatagramPacket(new byte[255], 255);
        MulticastSocket server = new MulticastSocket(9999);
        /*
         * 如果是发送数据报包,可以不加入多播组;
         * 如果是接收数据报包,必须加入多播组;
         * 这里是接收数据报包,所以必须加入多播组;
         */
        server.joinGroup(inetRemoteAddr);
        System.out.println("waiting......");
        while (true)
        {
            server.receive(recvPack);
            byte[] recvByte = Arrays.copyOfRange(recvPack.getData(), 0,
                    recvPack.getLength());
            System.out.println("Server receive:" + new String(recvByte));
        }
    }
    @Test
    public void main4() throws IOException
    {
        int port = 9999;
        byte[] msg = "connection successfully!!!".getBytes();
        //这里是个数据组，ip组，源码可看
        InetAddress inetRemoteAddr = InetAddress.getByName("224.0.0.5");
        MulticastSocket client = new MulticastSocket();
        DatagramPacket sendPack = new DatagramPacket(msg, msg.length, inetRemoteAddr, port);
        client.send(sendPack);
        System.out.println("end!");
        client.close();
    }
    @Test
    public  void main5()
    {
        int port = 9999;//开启监听的端口
        DatagramSocket ds = null;
        DatagramPacket dp = null;
        byte[] buf = new byte[1024];//存储发来的消息
        StringBuffer sbuf = new StringBuffer();
        try
        {
            //绑定端口的
            ds = new DatagramSocket(port);
            dp = new DatagramPacket(buf, buf.length);
            System.out.println("starting");
            ds.receive(dp);
            ds.close();
            int i;
            for(i=0;i<1024;i++)
            {
                if(buf[i] == 0)
                {
                    break;
                }
                sbuf.append((char) buf[i]);
            }
            System.out.println("Server receive：" + sbuf.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public  void main6() {
        //广播的实现 :由客户端发出广播，服务器端接收
        String host = "255.255.255.255";//广播地址
        int port = 9999;//广播的目的端口
        String message = "BroadcastTest.";//用于发送的字符串
        try
        {
            InetAddress adds = InetAddress.getByName(host);
            // while(true){
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(message.getBytes(),message.length(), adds, port);
            ds.send(dp);
            ds.close();
            // }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
