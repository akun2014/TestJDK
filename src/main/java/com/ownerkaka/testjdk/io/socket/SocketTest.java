package com.ownerkaka.testjdk.io.socket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;

/**
 * @Author akun
 * @Date 2021/8/21 11:57
 */
@Slf4j
public class SocketTest {

    @Test
    @SneakyThrows
    public void clientTest() {
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(9090);
        socket.connect(address);

        log.info("isConnected={}", socket.isConnected());
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true);
        String data = DateTime.now().toString("yyyy-MM-dd HH:mm:ss") + " hello";
        writer.println(data);
        log.info("response data to server");
    }

    @Test
    @SneakyThrows
    public void serverTest() {
        ServerSocket serverSocket = new ServerSocket(9090);
        log.info("start on 9090");
        while (true) {
            Socket socket = serverSocket.accept();
            //in/out stream
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringJoiner joiner = new StringJoiner("; ");
            String s = reader.readLine();
            while (s != null) {
                joiner.add(s);
                s = reader.readLine();
            }
            log.info("data={}", joiner);
        }
    }

}
