package atguigu.dao;/**
 * @author DingDi
 * @create 2022-07-19 10:30
 */

import atguigu.pojo.Book;

import java.util.List;

/**
 *
 * @author: dingdi
 * @date: 2022/7/19 10:30
 */
public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize,int min,int max);
}
