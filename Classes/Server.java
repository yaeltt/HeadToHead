
package project.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.classes.DataManager;


public class Server {
    
    public static DataManager dataManager=new DataManager();//מקור הנתונים
     static final String IP_ADDRESS = "localhost";
    public static final int PORT = 1500;;
    private ServerSocket serverSocket;
    private Play waitingGame;
    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);//הקמת נקודת קצה של שרת
            //port 1000//שיאזין דרך  
        } catch (IOException ex) {
            //במקרה שתהיה שגיאה
            //לדוגמה כי הפורט תפוס
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runServer() throws UnknownHostException {
          System.out.println("Server is running...");
        //הדפסת הכתובת והיציאה הלוגית
        System.out.println("IP Address:" + InetAddress.getLocalHost().getHostAddress());
        System.out.println("Port:" + serverSocket.getLocalPort());
        
        while (true) {
            try {
                //השרת מקבל בקשות מהלקוח
                Socket clientSocket = serverSocket.accept();//socket //מאזינה לבקשות התחברות וברגע שנוצר
                ServerThread thread=new ServerThread(clientSocket);
                thread.start();//התחלת תהליכון חדש שיטפל בלקוח החדש
                //כך שהשרת עצמו ממשיך להאזין ללקוח הבא

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

}
