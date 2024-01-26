import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User extends Person implements Serializable{
    
    protected static int Num_user = 0;
    protected int uniqueid;
    protected int age ;
    protected int reportcount ;
    protected String email;
    protected String password ;
    protected boolean Reported;

    
    public User(String name, Date date_of_birth, String email, String password) {
        super(name, date_of_birth);
        uniqueid = Num_user++;
        this.age = CalculateAge(date_of_birth);
        this.reportcount = 0;
       if(!checkForDuplicateEmail(email)){
            this.email = email;
       }

       else{
        System.out.println("email already exists ");
       }

        this.password = password;
        Reported = false;

    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getReportcount() {
        return reportcount;
    }

    public void setReportcount() {
        reportcount++;
        if(reportcount >= 3){

                // put user to reported User:
            Reported = true;
        }
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }




    public static int CalculateAge(Date d){
        return 2024 - d.year ;
    }

    // public static boolean CheckEmailAlreadyExists(){

    // }


     public static  void WriteUserTOFIle(User u ) throws NoSuchAlgorithmException{

        try {

            File f = new File("User.ser");
            ObjectOutputStream ooos; 
            u.setPassword(hashPassword(u.getPassword())); 
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

    public int getuniqueid(){
        return this.uniqueid;
    }


    
    public static ArrayList<User> ReadFromFile(){

        ArrayList <User> A = new ArrayList<User>();
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("User.ser"));

            while(true){
                    User p = (User)ois.readObject();
                    System.out.println(p.name);
                         A.add(p);
                      
            }                       
        }   
        catch(ClassNotFoundException e){
            System.out.println("error reading from file ");
           
        }
        catch(EOFException e){
          
        }
        catch(IOException e ){
            System.out.println("  hehe some error in reader IO exception ");     

        }
        return A;
    }


     public static boolean checkUserCreds(String email , String password) throws NoSuchAlgorithmException{
      
            // Your existing logic for checking user credentials
             ArrayList <User> A = ReadFromFile();

        for(int i = 0 ; i< A.size() ; i++){
            if(A.get(i).getEmail().equals(email) && A.get(i).getPassword().equals(hashPassword(password) )){
                        return true;
            }           
        }

         return false;
       
        } 
    



    
    private static String hashPassword(String pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes());

        // Convert byte array to hexadecimal representation
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }




    public static boolean checkForDuplicateEmail(String E){

        ArrayList<User> u = ReadFromFile();

            for(int  i = 0  ;  i < u.size() ; i++){
                if(u.get(i).email.equals(E)){
                    System.out.println("Already exist");
                    return true ;
                }

            }
            return false;
    }


    public static User ReturnForActiveUser(){
        ArrayList<User> u = ReadFromFile();
        ArrayList<CurrentUser> CU= CurrentUser.ReadCurrentFromFile();
       String cc = CU.get(0).u;
        for (int  i = 0 ; i < u.size() ; i++){
            if(u.get(i).name.equals(cc)){
                return u.get(i);
            }
        }
            return null;
    }



    public static User getCurrentUser(String email ){
        ArrayList <User> u = ReadFromFile();

        for(int i = 0 ; i < u.size() ; i++){
            if(u.get(i).email.equals(email) ){
                return u.get(i);
            }
        }
        return null;
    }

    
}
