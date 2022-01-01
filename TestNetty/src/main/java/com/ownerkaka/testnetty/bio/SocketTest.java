package com.ownerkaka.testnetty.bio;

import com.ownerkaka.testcommon.util.Constants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

/**
 * @Author akun
 * @Date 2021/8/21 11:57
 * 传统的阻塞io编程
 */
@Slf4j
public class SocketTest {

    @Test
    @SneakyThrows
    public void clientTest() {
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(Constants.PORT);
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
        ServerSocket serverSocket = new ServerSocket(Constants.PORT, 50, InetAddress.getLocalHost());

        log.info("start on {}", Constants.PORT);
        while (!Thread.interrupted()) {
            log.info("监听连接");
            Socket socket = serverSocket.accept();
            log.info("socket.isConnected={} hashCode={}", socket.isConnected(), socket.hashCode());
            new Thread(new Handler(socket)).start();
        }
    }

    static class Handler implements Runnable {

        final Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringJoiner joiner = new StringJoiner("; ");
                String s = reader.readLine();
                while (s != null) {
                    joiner.add(s);
                    s = reader.readLine();
                }
                log.info("data={}", joiner);
                byte[] input = new byte[Constants.MAX_INPUT_SIZE];
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException ignored) {

            } finally {
                IOUtils.closeQuietly(socket);
            }
        }

        private byte[] process(byte[] cmd) {
            return "hello".getBytes(StandardCharsets.UTF_8);
        }
    }


}
