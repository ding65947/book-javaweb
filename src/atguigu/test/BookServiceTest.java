package atguigu.test;

import atguigu.pojo.Book;
import atguigu.service.BookService;
import atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author DingDi
 * @create 2022-07-20 10:52
 */
public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"丁哥在手，天下我有！","1123",new BigDecimal(234),20,1,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);

    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"社会我丁哥，人狠话不多！","1123",new BigDecimal(234),20,1,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));

    }

    @Test
    public void queryBooks() {
        for(Book queryBook:bookService.queryBooks()){
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, 4));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, 4,10,50));
    }

}