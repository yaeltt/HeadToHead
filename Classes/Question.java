package project.classes;

import java.io.Serializable;


public class Question implements Serializable{
    
    private int codeStarter;
    private int queId;
   private String value;
   private String [] posibleAns;

    public int getCodeStarter() {
        return codeStarter;
    }

    public void setCodeStarter(int codeStarter) {
        this.codeStarter = codeStarter;
    }
   
   
   
    public Question(int id,String value, String [] posibleAns) {
        queId=id;
        this.value = value;
        this.posibleAns=posibleAns;
    }

    
    
    public String getValue() {
        return value;
    }
    public int getQueId() {
          return queId;
    }
    public String[] getPosibleAns() {
        return posibleAns;
    }

}