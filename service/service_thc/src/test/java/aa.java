import com.JaMorant.SSM.thc.utils.ConstantPropertiesUtil_thc;
import org.junit.Test;

/**
 * @author:JaMorant
 * @time:2023/2/24 16:59
 * @explain:
 */

public class aa {
    private String appID= ConstantPropertiesUtil_thc.ACCESS_KEY_ID ;
    private String appSecret = ConstantPropertiesUtil_thc.ACCESS_KEY_SECRET;
    @Test
    public void test(){
        System.out.println(appID);
        System.out.println(appSecret);
    }

}
