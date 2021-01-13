package com.qf.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qf.pojo.vo.Orders;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AlipayUtils {

    public String pay (HttpServletRequest httpRequest, HttpServletResponse httpResponse, Orders oder) throws ServletException, IOException {
//    public String pay (HttpServletRequest httpRequest, HttpServletResponse httpResponse, Course course) throws ServletException, IOException {
        /**
         * 1.要去支付包请求的地址
         */
        //
        // Double price = book.getPrice();

        AlipayClient alipayClient =  new DefaultAlipayClient( "https://openapi.alipaydev.com/gateway.do" , "2021000116666844", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC8wbjFvVxcdcp1tmuHXj+Q7rHBs/fha71JGsuT5oBqq5GYyD/ySg27b0ArLID5J0/EvU6ennfje9vzseRRd+4wnlqjnql2ztbjF4H/f2qdHY6UUOHMgMF02f4SPL0ELwvR2oVuZSse/xna+3aZQUkJ9dmQO8Po7U5rYmPaEyvR09cRHIPHSy83MrxnbVnXLL0wsdCqgWp35+EyeytX47dVSwKikZ3m3BgnMb727Ile/AouemGoNSROSpz/hnDG7TgWk7KwdihCrScvV0UBlNEK304EJUQIYuUb6b0vd9aMN/E49T0lfaEzCtFrKmVln9+pUrb5DQpJVNyoOi1nw7BBAgMBAAECggEADu8AsFxP71y+XNvG3Bnfy5am551WcjY07pC5JIVrCYVzanQFYyTquzaNDB1kQtISZI1ZqAD/mTbWawxa9PsZFC2jHCjUXXr101eryEwLYkzRV/iiNKzHIXiRul2l0qvqMnsobViBtd0W7ZVRh8R7g9c4lEm2MTp9Js/M+Jbls579H3yMGUkwKL3pEyINUjjoeBxvc0Q68c+ECtYWx49Q2NIA41je7EOgciDnHlgmoI5CRJv81ZcYW/dLpHgFQkN4S47iT2toJOB+4w50d9xPhltcb5K65ZgA6eM1mh9Tj3uPJYxzxXxYZUYNuyXUdnqRzWhZoe4HhQeL37W/MKsg1QKBgQDpYf3YJBeUviYCJxcVpFk4+fngkKzPgwF1HTn8EBpU3W0B+d0khUvdpc3AmhEx1z37GzwkFCH3s2yX9z+Fon3nzJaViIkwCkP2AcuCCpHlfIkcUYyxtdT5D7zRnedcpjlf/ywkQ0WQS7OCXLSM7CtOq1MxJe2LDkOgT5cYGHzuCwKBgQDPDJl7aHuRSvso5P2FNaESgZ9ShDI02dIjF5GVTYJdrByF/6m/MCm+ffd1iZNbcfl2qqh4QS5IWYOi/SKkhk5OBDcu6lJnWGwqiN6VegJoSgKETV9sb++0jrZNNmAnzNuH1uksiYn7ZVG6ur69WnKHN6iTF57O6olQ7BWJBRomYwKBgGlMNhzMA/uvYzS7cFXqZfsoBg7FF8M0p3awrhXFKiw6D1HB633rylQB1/hbSwDCzYs7OYJJtx2sTYWvH3Dq5IWuMA7doJAVQZ6AJtLQdA0niDFHtgQEN0f8SsQEy3to4CAo4ECoPX2iOibKLwgaElwXPpzBMRD5aZet8U0E13ELAoGAYZ/qOnMAWHiHRjf6hbXMtcIikjC8z4io+VziiwhZ4LsHU/NBYWFfPW99n5D3xxVNb7N17gWlwf1bAR3kGLku8MAyGM/pfMsHbWLNuKMnwW6sEx+8T4/T8UmdEXkDC6EfHcSqexJ62etjBu2IhBXCjJkkxf5MJfoWNu6qFMowE0sCgYAe2PDKBpZmy8h4S/MVwptY6HTxi6pNHIEBAkxVXJL5Cz8938WpEILkCOxXQLEHtEo3iVAJ1O7ymFYMNvMxJSImhLLgmJ8RVh2ReHP3/hJT1yl/ng3Q+tM+R1n0hR476lcM/IJkKa4E304mz668xr4Im3Xb6/ewQ471e7tQPkUuRQ==", "json","utf-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp8XVSXDG0GuJ+QahGTfCuam/BRnGbM1sycrYmJeW02YujGWc74bhFDRf70l4zcOpPBwT6mMHZPs/ka/lX/Zhjd4Un0Mp3cgFHOePtaOiab/4Ubs1RqVMjzxABGthkDQmN/oj3683T7LgKwn5h1miwx+eba2f8J6EGYfgzqFt+dv/+C99F3bZpI7ZOEHE+Yh7g9RJMcr4+8i3SvxUPlkI5baGYWvvBRZf1FjDQXyIX2kpIbDGoBcq1W8UveIWxNr/oJniCbpph93dJfFjlfR2fgVyXw3IvUmN2+SuPGRmzy3nMt71i3wInjJAWxjKRV4/m8a0r78aIUfbhdSHTZyAKwIDAQAB","RSA2");  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( "https://www.baidu.com/");
        alipayRequest.setNotifyUrl( "http://3605466d3d.qicp.vip:10413/returnUrl" ); //在公共参数中设置回跳和通知地址



        //生成订单信息存储到数据库，默认的订单信息时未支付
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+oder.getTransferId()+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":\""+oder.getPrice()+"\","  +
                "    \"subject\":\"购买课程\","  +
                "    \"body\":\"course\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset=UTF-8");
       /* httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();*/
        return form;
    }
}
