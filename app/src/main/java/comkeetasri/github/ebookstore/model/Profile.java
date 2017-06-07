package comkeetasri.github.ebookstore.model;

import java.util.ArrayList;

public class Profile {

    private  ArrayList<Book> myBook = new ArrayList<Book>();
    private double balance ;

    public Profile(){
        balance = 0;

    }

    public ArrayList<Book> getMyBook() {
        return myBook;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

}
