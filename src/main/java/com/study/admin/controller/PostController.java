package com.study.admin.controller;

import com.study.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class PostController {

    // HTML <Form>
    // ajax 검색
    // http 통신을 할 때 post body에다 data를 넣겠다
    // json, xml, multipart-form / text-plain

    @PostMapping(value = "/postMethod")
    public SearchParam PostMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put(){


    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }
}
