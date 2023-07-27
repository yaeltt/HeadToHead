package project.classes;

import java.util.ArrayList;
import java.util.List;

public class QuestionWhileGame {
   private Question myQuestion;//שאלה שנשלחה
   private List<String> answerdQuenstions;//תשובות שנענו עד כה
        

    public QuestionWhileGame(Question myQurstion) {
        this.myQuestion = myQurstion;
        this.answerdQuenstions = new ArrayList<String>();
    }
   
    public boolean checkAnswer(String ans){
        for(String item:myQuestion.getPosibleAns()){//מעבר על כל התשובות האפשריות לשאלה זו
            if(item.equals(ans)){
                if(answerdQuenstions==null||answerdQuenstions.size()==0)
                    return true;
                for(String i:answerdQuenstions){
                if(i.equals(ans))
                      return false;
                }
                return true;
            }
        }
        return false;
}

    public List<String> getAnswerdQuenstions() {
        return answerdQuenstions;
    }

    public Question getMyQuestion() {
        return myQuestion;
    }

    public void setAnswerdQuenstions(List<String> answerdQuenstions) {
        this.answerdQuenstions = answerdQuenstions;
    }

    public void addAnswerToList(String answer) {
        for (int i = 0; i < myQuestion.getPosibleAns().length; i++) 
            if(myQuestion.getPosibleAns()[i].contains(answer)&&answer.length()>=2)
                  this.answerdQuenstions.add(myQuestion.getPosibleAns()[i]);
    }
    
    public String fullAnswer(String s)
    {
        for (int i = 0; i < myQuestion.getPosibleAns().length; i++) 
            if(myQuestion.getPosibleAns()[i].contains(s)&&s.length()>=2)
                  return myQuestion.getPosibleAns()[i];
        return s;
    }
    
    public void setMyQuestion(Question myQuestion) {
        this.myQuestion = myQuestion;
    }
    
}


