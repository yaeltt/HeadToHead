package project.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Port;

public class Client extends Thread {

    private Socket socket;//שמאפשר להתחבר לנקודת קצה אחרת ברשת //JAVA //אובייקט של 
    //TCP\IP //בתצורת 

    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public Client() {
        try {
            //יצרת אובייקט שמתחבר לשרת עם הכתובת והיציאה המתאימה
            socket = new Socket(Server.IP_ADDRESS, Server.PORT);
            writer = new ObjectOutputStream(socket.getOutputStream());
            //אובייקט לצורך קריאת נתונים מהשרת
            reader = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*	public Question sendPlayerToStartGame(User u) {
     try {
     writer.writeObject(u);
     Question response = (Question) reader.readObject();
     return response;
     } catch (Exception e) {
     e.printStackTrace();
     }
     return null;
     }*/

    public Object sendMassage(Request r) {
        try {
            writer.writeObject(r);
            Object response = (Object) reader.readObject();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMassageWithoutRead(Request r) {
        try {
            writer.writeObject(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return null;
    }

    public Object read() {
        Object response = null;
        try {
            response = (Object) reader.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    public boolean readBoolean()
    {
        Boolean response=null;
        try {
            response = reader.readBoolean();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

}
