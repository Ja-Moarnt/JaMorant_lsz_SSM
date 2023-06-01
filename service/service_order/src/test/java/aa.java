import com.JaMorant.SSM.utils.OrderNoUtils;

/**
 * @author:JaMorant
 * @time:2023/2/3 22:22
 * @explain:
 */
public class aa {
    public static void main(String[] args) {
//        LocalDate date = LocalDate.now(); // get the current date
//        System.out.println(date);
//        System.out.println(date.getClass().getName());
        String orderNo = OrderNoUtils.getOrderNo();
        System.out.println(orderNo);
    }
}

