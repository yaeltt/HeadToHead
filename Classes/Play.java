package project.classes;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Play {
    private int code;
    private User u1;
    private User u2;
    private ObjectOutputStream writer1;
    private ObjectOutputStream writer2;
    private ObjectInputStream reader1;
    private ObjectInputStream reader2;
    private QuestionWhileGame qwg;
    private List<Integer> quesAsked;
    private Stock stock;
    private int count;
    
    public Play(User u1, User u2) {
        this.u1 = u1;
        this.u2 = u2;
        this.quesAsked = new ArrayList<Integer>();
        stock = new Stock();
        count = 0;
        
    }

    public Play() {
        this.quesAsked = new ArrayList<Integer>();
        stock = new Stock();
    }

    public List<Integer> getQuesAsked() {
        return quesAsked;
    }

    public QuestionWhileGame getQwg() {
        return qwg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    public Stock getStock() {
        return stock;
    }

    public User getU1() {
        return u1;
    }

    public User getU2() {
        return u2;
    }

    public void setU1(User u1) {
        this.u1 = u1;
    }

    public void setU2(User u2) {
        this.u2 = u2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Question randQuestion() {
        Random rand = new Random();
        int r = rand.nextInt(Stock.counter);
       // int r = 8;
        if (quesAsked.size() > 0) {
            for (int item : quesAsked) {
                if (item == r) {
                    while (r == item) {
                        r = rand.nextInt(Stock.counter);
                    }
                }
            }
        }
        Question Que = stock.getQuestionList().get(r);
        this.qwg = new QuestionWhileGame(Que);
        quesAsked.add(r);
        return Que;
    }

    public boolean checkAns(String ans) {
        if (qwg.checkAnswer(ans)) {
            return true;
        }
        return false;
    }

    public void setQuesAsked(List<Integer> quesAsked) {
        this.quesAsked = quesAsked;
    }

    public void setQwg(QuestionWhileGame qwg) {
        this.qwg = qwg;
    }

    /**
     * @return the writer1
     */
    public ObjectOutputStream getWriter1() {
        return writer1;
    }

    /**
     * @param writer1 the writer1 to set
     */
    public void setWriter1(ObjectOutputStream writer1) {
        this.writer1 = writer1;
    }

    /**
     * @return the writer2
     */
    public ObjectOutputStream getWriter2() {
        return writer2;
    }

    /**
     * @param writer2 the writer2 to set
     */
    public void setWriter2(ObjectOutputStream writer2) {
        this.writer2 = writer2;
    }

    /**
     * @return the reader1
     */
    public ObjectInputStream getReader1() {
        return reader1;
    }

    /**
     * @param reader1 the reader1 to set
     */
    public void setReader1(ObjectInputStream reader1) {
        this.reader1 = reader1;
    }

    /**
     * @return the reader2
     */
    public ObjectInputStream getReader2() {
        return reader2;
    }

    /**
     * @param reader2 the reader2 to set
     */
    public void setReader2(ObjectInputStream reader2) {
        this.reader2 = reader2;
    }

}
