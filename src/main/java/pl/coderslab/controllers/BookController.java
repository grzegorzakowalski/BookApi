package pl.coderslab.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.pojo.Book;
import pl.coderslab.services.BookService;
import pl.coderslab.services.MockBookService;

import java.util.Optional;

@Controller
@Slf4j
public class BookController {

    private BookService bookService;
    public BookController(MockBookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @ResponseBody
    private String getAllBooks() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(bookService.getBooks());
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    private String getBookById(@PathVariable("id") Long id) throws JsonProcessingException {
        Optional<Book> book = bookService.get(id);
        ObjectMapper objectMapper = new ObjectMapper();
        if(book.isPresent()){
            return objectMapper.writeValueAsString(book.get());
        }
        return null;
    }

    @PostMapping("/books")
    @ResponseBody
    private void addBook(@RequestBody Book book){
        log.info("{}", book);
        bookService.add(book);
    }

    @PutMapping("/books")
    @ResponseBody
    private void updateBook(@RequestBody Book book){
        bookService.update(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseBody
    private void deleteBookById(@PathVariable("id") Long id){
        bookService.delete(id);
    }


}
