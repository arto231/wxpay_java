package My;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import two_d_code.Create_TwoD_Code;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.test.WXPayConfigImpl;

public class Test {

    public static void main(String[] args) throws Exception {

        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "二吊来冲个一百万");
        int random=(int)((Math.random())*100000);//生成0-1之间随机数
        data.put("out_trade_no", "20160909105959010"+random);
        data.put("device_info", "web_test");
        data.put("fee_type", "CNY");
        int random_fee=(int)((Math.random())*1000);//生成0-1之间随机数
        data.put("total_fee", random_fee+"");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println("获取统一下单接口返回信息");
            for (String key : resp.keySet()) {
            	   System.out.println("["+ key + " = " + resp.get(key)+"]");
            	  }
            if(resp.get("code_url")!=null){
            	Create_TwoD_Code.testEncode(resp.get("code_url"),new SimpleDateFormat("YYYY-mm-dd,HH,MM,ss").format(new Date()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
