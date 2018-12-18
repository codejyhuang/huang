import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.config.ESCommon;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.dao.model.task.ShareInfo;
import com.hrym.rpc.association.AssociationAddService;
import com.hrym.rpc.auth.api.LoginService;
import com.hrym.rpc.auth.api.TaskAddService;
import com.hrym.rpc.auth.api.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mj on 2017/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dubbo-provider.xml","classpath:spring/applicationContext-jdbc.xml","classpath:mybatis-config.xml"})
public class BaseTest {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TaskAddService taskAddService;
    @Autowired
    private AssociationAddService associationAddService;
    @Autowired
    private TaskService taskService;

    @Before
    public void setUp(){
        System.out.println("=======@before=======");
    }




    @Test
    public void testUpdateUserInfo(){

//        BaseResult baseResult = associationAddService.getAssociationHomepage(1);
//        if (baseResult!=null){
//            System.out.print(baseResult);
//        }else {
//            System.out.print("空");
//        }
//
    }


    @Test
    public void testFindLoginInfoByuuid(){

        ShareInfo share = new ShareInfo();
        share.setTaskId(1);
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
