package project.classes;
import java.util.List;
import java.util.Vector;

public class Stock {
    public static int counter=10;
    private List<Question> questionList;
    public Stock() {
        this.questionList = new Vector<Question>();
           questionList.add(new Question(0,"ששת רכיבי התזונה שגוף האדם צריך", new String[]{"פחמימות", "חלבונים", "שומנים","ויטמינים","מינרלים", "מים"}));
          questionList.add(new Question(1,"ראשי ממשלת ישראל", new String[]{"דוד בן גוריון", "משה שרת", "לוי אשכול", "גולדה מאיר", "יצחק רבין", "מנחם בגין", "יצחק שמיר", "שמעון פרס", "בנימין נתניהו", "אהוד ברק", "אריאל שרון", "אהוד אולמרט", "נפתלי בנט", "יאיר לפיד", "יגאל אלון"}));
        questionList.add(new Question(2,"חודשים עיבריים", new String[]{"תשרי", "חשון", "כסליו", "טבת", "שבט", "אדר", "ניסן", "אייר", "סיוון", "תמוז", "אב", "אלול"}));
        questionList.add(new Question(3,"חודשים לועזיים", new String[]{"ינואר", "פברואר", "מרץ", "אפריל", "מאי", "יוני", "יולי", "אוגוסט", "ספטמבר", "אוקטובר", "נובמבר", "דצמבר"}));
        questionList.add(new Question(5,"יבשות", new String[]{"אפריקה", "אירופה", "אסיה", "אמריקה הצפונית", "אמריקה הדרומית", "אנטרטיקה", "אוסטרליה"}));
        questionList.add(new Question(5,"אוקיינוסים בעולם", new String[]{"השקט", "הדרומי", "האטלנטי", "ההודי", "הקרח הצפוני"}));
        questionList.add(new Question(6,"סוגי דגים", new String[]{"דניס", "אמנון", "מושט", "טונה", "סלמון", "בורי", "קרפיון", "כסיף", "בס", "לברק", "פורל", "מקרל", "מוסר"}));
        questionList.add(new Question(7,"חודשים לועזיים", new String[]{"ינואר", "פברואר", "מרץ", "אפריל", "מאי", "יוני", "יולי", "אוגוסט", "ספטמבר", "אוקטובר", "נובמבר", "דצמבר"}));
        questionList.add(new Question(8,"חמשת החושים של האדם", new String[]{"ראיה","שמיעה","מישוש","טעם","ריח"}));
        questionList.add(new Question(9,"שישה צבעי הקשת", new String[]{"סגול","אדום","כחול","ירוק","כתום","צהוב"}));
       
        counter=questionList.size();
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
    
}