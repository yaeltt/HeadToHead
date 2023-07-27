package project.classes;

import java.awt.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;

public class DataManager {
   private Vector<User> UsersList;
   private Stock  stock;
    public DataManager() {
        this.UsersList=new Vector<User>();
         UsersList.add(new User(1,"יעל טובול","1", 0));
          UsersList.add(new User(2,"יעל נגר","2", 0));
        UsersList.add(new User(3,"גילי אוחנה","gili1", 0));
        UsersList.add(new User(4,"אתי חזן","eti1", 0));
        UsersList.add(new User(5,"אפרת דיין","efi1234", 0));
        UsersList.add(new User(6,"תאיר","tair1234", 0));
        UsersList.add(new User(7,"יעל אמזלג","yael1234", 0));
        this.stock=new Stock();
    }

public Vector<User> getUsersList(){
    return UsersList;
}

     public User getUserById(int id){
         for(User item:UsersList)
         {
              if(item.getId()==id){
                  item.setPoints(0);
             return item;
              }
         }
         return null;
     }
     
     public boolean sighnUP(User usr){
        if(getUserById(usr.getId())==null) return false;
         UsersList.add(usr);
         return true;
     }
     
      public User logIn(int id, String password){
        if(getByIdAndPassword(id,password)==null) return null;
        User u=getUserById(id);
         return u;
     }
    public User getByIdAndPassword(int id,String pass)
    {
        if(!(this.UsersList.stream().anyMatch(x->x.getId()==id&&x.getPassword().equals(pass))))
            return null;
       return UsersList.stream().filter(d->d.getId()==id && d.getPassword().equals(pass)).findAny().get();
    }
     

     public boolean updateUser(User u){
         User myUser=getUserById(u.getId());
         if(myUser!=null)
             {
                 myUser.setUserName(u.getUserName());
                 myUser.setPassword(u.getPassword());
                 myUser.setPoints(u.getPoints());
                 return true;
             }
         return false;
     }
     public String Answer(String q){
         return q;
     }
}
    
