import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class ReportedUser implements Serializable {

    public ReportedUser(User u){
        WriteReportedUserTOFIle(u);
    }

     public static  void WriteReportedUserTOFIle(User u ) {

        try {

            File f = new File("ReportedUser.ser");
            ObjectOutputStream ooos; 
            if(f.exists()){
                ooos = new MyObjectOutputStream(new FileOutputStream(f , true ));
            }
            else{
                ooos = new ObjectOutputStream(new FileOutputStream(f));
            }
            ooos.writeObject(u);
            ooos.close();
        }

        catch(IOException e ){
            System.out.println("error while writing file ");
        }
    }





      
    public static ArrayList<User> ReadFromFile(){

        ArrayList <User> A = new ArrayList<User>();
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ReportedUser.ser"));

            while(true){
               
              //  Object p = (Object)ois.readObject();
              //  if(p instanceof Patient){
                    User p = (User)ois.readObject();
                   
                    WriteReportedUserTOFIle(p);
                       // }          
            }                       
        }   
        catch(ClassNotFoundException e){
            System.out.println("error reading from file ");
           

        }
        catch(EOFException e){
          
        }
        catch(IOException e ){
            System.out.println("some error in reader IO exception ");     

        }
        return A;
    }

// check if user is reported 

public static boolean CheckUserReported(String email){

    ArrayList<User> U1 =   ReadFromFile();

    for(int i = 0 ; i < U1.size() ; i++){
        if(U1.get(i).email == email){
            return true ;          
        }
        
    }
        return false ;
}

public static void Reported2NormalUser(int id ){
        ArrayList<User> U1 =   ReadFromFile();

         for(int i = 0 ; i < U1.size() ; i++){
          if(U1.get(i).uniqueid == id){
            U1.remove(i);          
               }     
    }


    for(int j = 0 ; j< U1.size() ; j++){
        WriteReportedUserTOFIle(U1.get(j));
    }
}

    
}
