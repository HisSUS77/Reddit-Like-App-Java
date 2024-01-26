import java.io.Serializable;

class Person implements Serializable {
    
   
    protected String name ;
    protected Date Date_of_birth ;
    



    public Person(String name, Date date_of_birth) {
        this.name = name;
        Date_of_birth = new Date(date_of_birth);
    }

    public String toString() {
        return "Person [name=" + name + ", Date_of_birth=" + Date_of_birth + "]";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate_of_birth() {
        return Date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        Date_of_birth = date_of_birth;
    }

    

}