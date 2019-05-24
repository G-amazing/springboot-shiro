package com.gamazing.springbootshirodemo.entity;

import lombok.Data;

@Data
public class Perm {
    private Integer id;
    private String userId;
    private String permission;
}
