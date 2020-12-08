package com.study.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 매개변수를 가지는 생성자 추가
public class SearchParam {

    private String account;
    private String email;
    private int page;
}
