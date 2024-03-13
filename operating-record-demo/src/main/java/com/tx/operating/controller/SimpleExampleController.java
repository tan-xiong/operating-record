package com.tx.operating.controller;

import com.alibaba.fastjson.JSON;
import com.tx.operating.annotation.LogRecordAnnotation;
import com.tx.operating.entity.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiong
 * @date 2023/11/8 10:36
 */
@Slf4j
@RestController
@RequestMapping("operating")
public class SimpleExampleController {

    @RequestMapping("/record")
    @LogRecordAnnotation(bizNo = "DSO024131200015",detail = "{#Example.user.name}将地址从`{DefaultFunction{#Example.address}}`修改为{#Example.address}",category = "修改",operator = "{#Example.user.name}")
    public void modifyAddress(@RequestBody Example example){
        log.info("/// 用户修改地址参数{}",JSON.toJSONString(example));
    }

}
