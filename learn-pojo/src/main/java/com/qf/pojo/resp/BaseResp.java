package com.qf.pojo.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResp {

    private Integer code;

    private Object data;

    private String message;

    private Long total;

    private String name;


}
