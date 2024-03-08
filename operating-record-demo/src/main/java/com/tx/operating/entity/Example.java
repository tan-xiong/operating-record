package com.tx.operating.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tanxiong
 * @date 2023/11/8 14:04
 */
@Data
public class Example {

    private String address;

    private User user;

    private List<Detail> details;

    @Data
    public static class User {
        private String name;
        private BigDecimal age;
    }

    @Data
    public  static class Detail{
        private BigDecimal number;
    }

}
