package project.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//מחלקה שבה תוגדר תקשורת של השרת עם לקוח מסוים
public class ServerThread extends Thread {

    private Socket clientSocket;
    private static Play play;
    private static Object mutex = new Object();
    private int codeAnswerer;
    private List<Message> res;
    static int numInCorrect = 0;
    static int code=0;
    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.res = new ArrayList<Message>();
    }

    @Override
    public void run() {
        int num = 1;
       

        User thisUser = null;

        try {
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream()); //באופן זה קריאת הנתונים היא מהלקוח החדש שהתחבר
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("new connecrion from:" + clientSocket.getInetAddress().getHostName());
            Request reqFromClient = (Request) reader.readObject();
            while (reqFromClient.getType() != TypeOfRequest.StopConnection)//כל עוד נשלחה בקשה ששונה מבקשת סיום התקשרות
            {
                switch (reqFromClient.getType()) {
                    case LogIn:
                        Serializable[] arr = (Serializable[]) reqFromClient.getDataOfRequest();
                        int code = (int) arr[0];
                        String pass = (String) arr[1];
                        thisUser = Server.dataManager.logIn(code, pass);
                        writer.writeObject(thisUser);
                        while (thisUser == null) {
                            reqFromClient = (Request) reader.readObject();
                            arr = (Serializable[]) reqFromClient.getDataOfRequest();
                            code = (int) arr[0];
                            pass = (String) arr[1];
                            thisUser = Server.dataManager.logIn(code, pass);
                            writer.writeObject(thisUser);

                        }
                        synchronized (mutex) {
                            if (thisUser != null && (play == null || (play.getU1() != null && play.getU2() != null)))//צריך לפתוח משחק חדש
                            {
                                play = new Play();
                                play.setU1(thisUser);
                                play.setWriter1(writer);
                                play.setReader1(reader);
                                num = 1;
                            } else {
                                play.setU2(thisUser);
                                play.setWriter2(writer);
                                play.setReader2(reader);
                                num = 2;
                                writer.writeObject(play.getU1());
                                play.getWriter1().writeObject(thisUser);
                                Question q = play.randQuestion();
                                play.setCount(play.getCount()+1);
                                q.setCodeStarter(play.getU1().getId());
                                play.getWriter1().writeObject(q);
                                play.getWriter2().writeObject(q);
                            }
                        }

                        break;
                    case Answer:
                        String ans = play.getQwg().fullAnswer(String.valueOf(reqFromClient.getDataOfRequest()));
                        boolean flag = play.getQwg().checkAnswer((String) ans);
                        codeAnswerer = thisUser.getId();
                        if (!flag) {
                            numInCorrect++;
                        } else {
                            play.getQwg().addAnswerToList((String) ans);
                            if (play.getU1().getId() == thisUser.getId()) {
                                play.getU1().setPoints(play.getU1().getPoints() + 1);
                            } else {
                                play.getU2().setPoints(play.getU2().getPoints() + 1);
                            }
                        }
                        Message m ;
                        if(thisUser.getId()==play.getU1().getId())
                        m= new Message(ans, codeAnswerer, flag,play.getU1().getPoints(),play.getU2().getPoints());
                        else
                            m= new Message(ans, codeAnswerer, flag,play.getU2().getPoints(),play.getU1().getPoints());
                        play.getWriter1().writeObject(m);
                        play.getWriter2().writeObject(m);
                        if (numInCorrect == 2 || (play.getQwg().getAnswerdQuenstions() != null && play.getQwg().getAnswerdQuenstions().size() == play.getQwg().getMyQuestion().getPosibleAns().length)) {
                            //אם יש שני תשובות לא נכונות או שכל התשובות נענו
                            if (play.getCount() >= 2) {//אם נשאלו שתי שאלות נגמר המשחק
                                if (play.getU1().getPoints() > play.getU2().getPoints()) {
                                    play.getWriter1().writeObject("המנצח הוא: " + play.getU1().getUserName());
                                    play.getWriter2().writeObject("המנצח הוא: " + play.getU1().getUserName());
                                } else if (play.getU2().getPoints() > play.getU1().getPoints()) {
                                    play.getWriter1().writeObject("המנצח הוא: " + play.getU2().getUserName());
                                    play.getWriter2().writeObject("המנצח הוא: " + play.getU2().getUserName());
                                } else {
                                    play.getWriter1().writeObject("הניקוד הוא תיקו");
                                    play.getWriter2().writeObject("הניקוד הוא תיקו");
                                }
                              
                            } else {//אחרת תגריל שאלה נוספת
                                Question q = play.randQuestion();
                                play.getQwg().setAnswerdQuenstions(new ArrayList<String>());
                                play.setCount(play.getCount()+1);
                                numInCorrect = 0;
                                if (codeAnswerer == play.getU1().getId()) {
                                    q.setCodeStarter(play.getU2().getId());
                                } else {
                                    q.setCodeStarter(play.getU1().getId());
                                }
                                play.getWriter1().writeObject(q);
                                play.getWriter2().writeObject(q);
                            }
                        }
                        break;
                }
                
                reqFromClient = (Request) reader.readObject();
            }
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
        }
    }
}
