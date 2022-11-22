package atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author DingDi
 * @create 2022-06-30 20:52
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;

    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            Properties properties=new Properties();
            //读取配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * 如果返回null，说明获取连接失败，有值就是成功
     * */
    public static Connection getConnection(){
        Connection conn = conns.get();

        if(conn==null){
            try {
                conn=dataSource.getConnection();  //从数据库连接池中获取连接
                conns.set(conn);     //保存到ThreadLocal对象中,供后面的JDBC操作使用
                conn.setAutoCommit(false);  // 设置为手动管理事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return conn;
    }


    /**
     * 提交事务并关闭连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection!=null){//如果不等于null，说明之前使用过连接，操作过数据库

            try {
                connection.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接释放资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
        //一定要执行remove操作，否则就会出错(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }


    /**
     * 回滚事务
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection!=null){//如果不等于null，说明之前使用过连接，操作过数据库

            try {
                connection.rollback();//回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接释放资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
        //一定要执行remove操作，否则就会出错(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();

    }







    /**
     * 关闭连接，放回数据库连接池
     */
//    public static void closs(Connection conn){
//        if(conn!=null){
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//
//    }
}
