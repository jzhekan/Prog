package com.company;

import java.lang.Thread;

public class MainServer {
    public static void main(String[] args) {
        final HTTPServer server = new HTTPServer(8080);
        server.setPath("D:\\JavaProjects\\Prog\\httpserver\\src\\com\\company");
        server.start();

        System.out.println("Server started...");


        //действия по завершению
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                server.stop();
                System.out.println("Server stopped!");
            }
        });
    }


}