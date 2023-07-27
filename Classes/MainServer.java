package project.classes;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServer {

    public static void main(String[] args) {
        Server s=new Server();
        try {
            s.runServer();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
