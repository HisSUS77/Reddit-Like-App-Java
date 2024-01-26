import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    private static int nextPostId = 1;

    private int postId;
    private String SubredditTitle;  
    private String postContent;
    private String userIdWhoPosted;
    private int likeCount;
    private ArrayList<String> comments;


    public Post(String postTitle, String postContent, User u) {
        this.postId = nextPostId++;
        this.SubredditTitle = postTitle;
        this.postContent = postContent;
        this.userIdWhoPosted = u.getName();
        this.likeCount = 0;
        this.comments = new ArrayList<>();

    }

    
    public int getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return SubredditTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getUserIdWhoPosted() {
        return userIdWhoPosted;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void incrementLike() {
        likeCount++;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }


public static ArrayList<Post> getPostsByTitle(String e){

        ArrayList<Post> p = ReadPosts();
        ArrayList<Post> sameTitle = new ArrayList<Post>();
        for(int i = 0; i< p.size() ; i++){
            if(p.get(i).SubredditTitle.equals(e)){
                sameTitle.add(p.get(i));
            }
        }

        return sameTitle;
}

public static void Uploadpost(Post p){

        try {
            File f = new File("Posts.ser");
            ObjectOutputStream ooos; 
            if(f.exists()){
                ooos = new MyObjectOutputStream(new FileOutputStream(f , true ));
            }
            else{
                ooos = new ObjectOutputStream(new FileOutputStream(f));
            }
            ooos.writeObject(p);
            ooos.close();
        }
        catch(IOException e ){
            System.out.println("error while writing file ");
        }

    }



      public static ArrayList<Post> ReadPosts(){

        ArrayList <Post> A = new ArrayList<Post>();
        
        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Posts.ser"));

            while(true){
                
                    Post p = (Post)ois.readObject();
                     if (  p instanceof Post){
                    System.out.println(p.SubredditTitle);
                         A.add(p);
                    }
                 
                     
            }                       
        }   
        catch(ClassNotFoundException e){
            System.out.println("error reading from file ");
           

        }
        catch(Exception e){
        }
        return A;
    }


public static ArrayList<Post> ReturnCurrentUserPost(){

    ArrayList<Post> post = ReadPosts();
    ArrayList<Post> currentUserPost = new ArrayList<Post>();
    for(int i = 0 ; i < post.size() ; i++){
        if(CurrentUser.ReturnActiveUser().u.equals(post.get(i).userIdWhoPosted)){
            currentUserPost.add(post.get(i));
        }
    }

    return currentUserPost;
}



      }


