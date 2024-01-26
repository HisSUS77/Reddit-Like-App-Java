import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CurrentUser implements Serializable {


    String u;

    public CurrentUser(String e) {
        u = e;
    }


    public static void WriteCurrentUser(CurrentUser u) {
        
        try {
            File f = new File("CurrentUser.ser");
            ObjectOutputStream ooos;
            if (f.exists()) {
                ooos = new MyObjectOutputStream(new FileOutputStream(f, true));
            } else {
                ooos = new ObjectOutputStream(new FileOutputStream(f));
            }
            ooos.writeObject(u);
            ooos.close();
        } catch (IOException e) {
            System.out.println("error while writing file ");
        }

    }

    public static ArrayList<CurrentUser> ReadCurrentFromFile() {

        ArrayList<CurrentUser> A = new ArrayList<CurrentUser>();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("CurrentUser.ser"));

            while (true) {

                CurrentUser p = (CurrentUser) ois.readObject();
                if (p instanceof CurrentUser) {
                    A.add(p);
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("error reading from file ");

        } catch (Exception e) {
        }
        return A;
    }

    public static CurrentUser ReturnActiveUser() {
        ArrayList<CurrentUser> cu = ReadCurrentFromFile();

        if (!cu.isEmpty()) {

            return cu.get(1);
        }
        return null;
    }

}
