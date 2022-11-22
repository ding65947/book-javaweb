package atguigu.service;/**
 * @author DingDi
 * @create 2022-07-20 10:43
 */

import atguigu.pojo.Book;
import atguigu.pojo.Page;

import java.util.List;

/**
 *
 * @author: dingdi
 * @date: 2022/7/20 10:43
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
