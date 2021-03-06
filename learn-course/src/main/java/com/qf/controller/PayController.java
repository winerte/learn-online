package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qf.dao.OrdersMapper;
import com.qf.pojo.vo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class PayController {

    @Autowired
    OrdersMapper orderMapper;



    @RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request , HttpServletRequest httpResp) throws AlipayApiException {
        Map<String, String> stringStringMap = convertRequestParamsToMap(request);
//        System.out.println(stringStringMap);
        //支付宝公钥
            boolean signVerified = AlipaySignature.rsaCheckV1(stringStringMap, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp8XVSXDG0GuJ+QahGTfCuam/BRnGbM1sycrYmJeW02YujGWc74bhFDRf70l4zcOpPBwT6mMHZPs/ka/lX/Zhjd4Un0Mp3cgFHOePtaOiab/4Ubs1RqVMjzxABGthkDQmN/oj3683T7LgKwn5h1miwx+eba2f8J6EGYfgzqFt+dv/+C99F3bZpI7ZOEHE+Yh7g9RJMcr4+8i3SvxUPlkI5baGYWvvBRZf1FjDQXyIX2kpIbDGoBcq1W8UveIWxNr/oJniCbpph93dJfFjlfR2fgVyXw3IvUmN2+SuPGRmzy3nMt71i3wInjJAWxjKRV4/m8a0r78aIUfbhdSHTZyAKwIDAQAB", "utf-8", "RSA2");
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
//            System.out.println(stringStringMap);

            if (stringStringMap.get("trade_status").equals("TRADE_SUCCESS")){
                //根据out_trade_no 修改 订单状态为已支付
                String out_trade_no = stringStringMap.get("out_trade_no");
                //通过订单号去数据库查询出该笔订单
                Orders byTransferId = orderMapper.findByTransferId(out_trade_no);
                //比对金额
                Double total_amount =Double.valueOf(stringStringMap.get("total_amount")) ;

                if (total_amount.toString().equals(byTransferId.getPrice().toString())){
                    byTransferId.setStatus("已支付");
//                    bytransferId.setPayId(stringStringMap.get("trade_no"));
                    orderMapper.updateById(byTransferId);
                }
            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }
}
