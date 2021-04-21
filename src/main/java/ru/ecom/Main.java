package ru.ecom;

import com.sun.net.httpserver.HttpServer;
import ru.ecom.service.DocumentHttpHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args){
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8765), 0);
            server.createContext("/document", new DocumentHttpHandler());
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
