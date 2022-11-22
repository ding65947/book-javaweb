package atguigu.test;

import atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author DingDi
 * @create 2022-06-30 21:49
 */
public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils(){
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
//        JdbcUtils.closs(conn);
    }
}
