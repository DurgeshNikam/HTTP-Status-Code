package com.api.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.book.entities.Book;
@Component
public class BookService {
	
		private static List<Book> list = new ArrayList<>();
	
					static {
						list.add(new Book(1,"Complete java Reference", "xyz"));
						list.add(new Book(2,"Head First To JAVA", "abc"));
						list.add(new Book(3,"Think in java", "pqr"));
						list.add(new Book(4,"Think in pyhton", "ddd"));
					}
				 
											public List<Book> getAllBooks()
											{
												return list;
											}
			//=======================================================================================							
											public Book getBookById(int id)
											{
												Book book=null;
												try {
												book=list.stream().filter(e->e.getId()==id).findFirst().get();
												}
												catch(Exception e)
												{
													e.printStackTrace();
												}
												return book;
											}
											
			//=======================================================================================								
											public Book addBook(Book b)
											{
												list.add(b);
												return b;
											}
			//=======================================================================================								
											public void deleteBook(int bid)
											{
												list=list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
												
											}
			//=======================================================================================						
											public void updatebook(Book book, int bookId) {
												list = list.stream().map(b->{
													if(b.getId()==bookId) {
														b.setTitle(book.getTitle());
														b.setAuthor(book.getAuthor());
													}return b;
												}).collect(Collectors.toList());
												
											}
					
}




