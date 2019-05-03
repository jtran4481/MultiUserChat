package com.muc;

import java.io.*;

import java.net.Socket;
import java.util.Date;

public class ServerWorker extends Thread {

    private final Socket clientSocket;

    public ServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void handleClientSocket() throws IOException, InterruptedException {
        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            if (tokens != null && tokens.length > 0) {


                String cmd = tokens[0];
                if ("quit".equalsIgnoreCase(line)) {
                    break;
                } else {

                }
                String msg = "You typed: " + line + "\n";
                outputStream.write(msg.getBytes());
            }
            clientSocket.close();
        }

    }
}