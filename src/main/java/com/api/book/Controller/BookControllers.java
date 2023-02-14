package com.api.book.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.service.BookService;

@RestController 
public class BookControllers {
	
	
	@Autowired  
	private BookService bookService; 
	
	
						//get All Books Handler.......
					@GetMapping("/books") 
					public ResponseEntity<List<Book>> getBooks()
					{
						Book book = new Book();
						
						List<Book>list=bookService.getAllBooks();
						
								if(list.size()<=0)
								{
									return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
								}
								
						return ResponseEntity.of(Optional.of(list));
					}
			//====================================================================================	
										//Get single Book handler.....
										@GetMapping("/books/{id}")
										public ResponseEntity<Book> getBook(@PathVariable (value="id") int id)
										{
											Book book = bookService.getBookById(id);
											if(book==null)
											{
												return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
											}
											return ResponseEntity.of(Optional.of(book));
										}
										
//######Solution on get book by id#####										//getbyId problem solved .....
//										@GetMapping("/books/{id}")
//										public ResponseEntity<Book> getBook(@PathVariable (value="id") int id)
//										{
//											Book book = bookService.getBookById(id);
//											if(book==null)
//											{
//												return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//											}
//											return ResponseEntity.of(Optional.of(book));
//										}
			//====================================================================================
										//Post New Book Mapping Handler....
					@PostMapping("/books")
					public ResponseEntity<Book> addBook(@RequestBody Book book)
					{
						Book b = null;
						
						try {
							b=this.bookService.addBook(book);
							System.out.println(b);
							return ResponseEntity.of(Optional.of(b));
						} catch (Exception e) {
							e.printStackTrace();
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
						}
						
					}
			//====================================================================================
											//Delete Book Handler.......
										@DeleteMapping("/books/{bookid}")
										public ResponseEntity<Void> deleteBook(@PathVariable ("bookid") int bookid)
										{
											try {
												this.bookService.deleteBook(bookid);
												//return ResponseEntity.ok().build();
												return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
											} catch (Exception e) {
												e.printStackTrace();
												
												return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
											}
										}
			//====================================================================================
					@PutMapping("/books/{bookId}")
					public ResponseEntity<Book> updateBook(@RequestBody Book book , @PathVariable  ("bookId") int bookId)
					{
						try {
							this.bookService.updatebook(book,bookId);
							return ResponseEntity.ok().body(book);
						} catch (Exception e) {
							e.printStackTrace();
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
						}
						
					}
					
}





