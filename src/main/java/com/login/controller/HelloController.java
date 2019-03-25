package com.login.controller;

import com.login.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/a/{i}")
    public int a(@PathVariable Integer i) {
        System.out.println("a");
        cacheService.put("cns", "i", i);
        return 1;
    }

    @RequestMapping("/b/{i}")
    public int b(@PathVariable Integer i) {
        System.out.println("b");
        return cacheService.get("cns", "i");
    }

}
