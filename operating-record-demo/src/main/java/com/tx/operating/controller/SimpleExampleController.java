package com.tx.operating.controller;

import com.alibaba.fastjson.JSON;
import com.tx.operating.annotation.LogRecordAnnotation;
import com.tx.operating.entity.Example;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiong
 * @date 2023/11/8 10:36
 */
@RestController
@RequestMapping("operating")
public class SimpleExampleController {

    @RequestMapping("/record")
    @LogRecordAnnotation(detail = "121212121,#{address}")
    public void modifyAddress(@RequestBody Example json){
        int i=1/0;
        System.out.println(JSON.toJSONString(json));
    }

}
