package com.example.sentinelexample.controller;


import com.alibaba.csp.sentinel.adapter.servlet.config.WebServletConfig;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class TestController {


    @GetMapping("/hello")
    @SentinelResource(value = "doSomething",blockHandler = "handleException",blockHandlerClass ={ExceptionUtil.class} )
    public String doSomething() {
       String xxx = WebServletConfig.getBlockPage ();
        System.out.println(xxx);

        return "Hello ";
    }


    public static String handleBlock(BlockException e) {
        return "Pengunjung sedang penuh, mohon mencoba kembali nanti ...!!";
    }



    @GetMapping("/test")
    public void test() {
        int counter =1;
        AtomicReference<String> responseMessage = new AtomicReference<>("");
        while (counter!=100){
            CompletableFuture.runAsync(()-> {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response =  restTemplate.getForEntity("http://localhost:8088/hello",String.class);
                System.out.println(response.getBody());
                System.out.println(response.getStatusCode());
                responseMessage.set(response.getBody());
            });
            counter++;
        }
    }

}
