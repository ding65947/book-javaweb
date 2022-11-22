package atguigu.utils;/**
 * @author DingDi
 * @create 2022-07-07 22:08
 */

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 *
 * @author: dingdi
 * @date: 2022/7/7 22:08
 */
public class WebUtils {

    /**
     * 把map中的值注入到对应的javaBean属性中
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }


    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultValue;

    }

}
