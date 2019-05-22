/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;
/**
 *
 * @author User
 */
public abstract class books {
    int bookId;
    String bookName;
    String author;
    String publisher;
    int edition;
    int number;
    abstract void displayBookDetails();
    abstract int searchById(int i);
}
