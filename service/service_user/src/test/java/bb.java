import com.JaMorant.SSM.user.utils.ConstantPropertiesUtil;
import org.junit.Test;

/**
 * @author:JaMorant
 * @time:2023/3/1 9:21
 * @explain:
 */
public class bb {
    private String appID= ConstantPropertiesUtil.ACCESS_KEY_ID ;
    private String appSecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
    @Test
    public void test(){
        System.out.println(ConstantPropertiesUtil.ACCESS_KEY_ID);
        System.out.println(appSecret);
    }
}
