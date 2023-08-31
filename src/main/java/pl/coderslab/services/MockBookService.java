package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.pojo.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MockBookService implements BookService{
    private List<Book> list;
    private List<Long> idList;
    private static Long next_id;

    public MockBookService(){
        list = new ArrayList<>();
        idList = new ArrayList<>();
        idList.add(1l);
        idList.add(2l);
        idList.add(3l);
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa Java.", "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion","programming"));
        next_id = 4l;
    }
    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public Optional<Book> get(Long id) {
        int index = -1;
        Optional optional;
        for (int i = 0; i < idList.size(); i++){
            if( idList.get(i) == id){
                index = i;
                break;
            }
        }
        if (index != -1){
            optional = Optional.of(list.get(index));
        } else {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public void add(Book book) {
        book.setId(next_id);
        idList.add(next_id);
        next_id++;
        list.add(book);
    }

    @Override
    public void delete(Long id) {
        list.removeIf(book -> book.getId() == id);
        idList.removeIf( aLong -> aLong == id);
    }

    @Override
    public void update(Book book) {
        int index = idList.indexOf(book.getId());
        if( index != -1){
            list.set(index, book);
        }
    }
}
