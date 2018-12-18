
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.ManagerParam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mj on 2017/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dubbo-comsume.xml","classpath:spring/applicationContext-jdbc.xml"})
public class BaseTest {

    @Before
    public void setUp(){
        System.out.println("=======@before=======");
    }



    @Test
    public void test(){
       ManagerParam param = new ManagerParam();
       //com.hrym.rpc.app.util.Result result=mcategoryService.levelFindTaskContent(param);
        //System.out.println(result);
    }
    @Test
    public void testUpdateUserInfo(){

      ManagerParam param = new ManagerParam();

        //Result result = mcategoryService.categoryManage(param);
       //System.out.println(result);
    }


    @Test
    public void testFindLoginInfoByuuid(){


        System.out.println("bt");

    }

    @Test
    public void testRedis(){
        String key = "name";
        String value = "小明";
        RedisUtil.set(key,value,RedisUtil.EXRP_HALF_HOUR);

        String value1 = RedisUtil.get(key);
        System.out.println(value1);
    }


}
