package com.server.a.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.a.dto.Person;
import com.server.a.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/a")
public class MainControllerA {

    static List<Map<String,Long>> mapList=new ArrayList<>();

   @Autowired
   private MyService myService;


   @Value("classpath:/json/sample.json")
   private Resource resource;

   @Autowired
   ObjectMapper objectMapper;


    @GetMapping("/resource")
    public ResponseEntity<?> ge2t() throws IOException {
       var str = resource.getContentAsString(StandardCharsets.UTF_8);
        return ResponseEntity.ok(objectMapper.readValue(str,

                Person.class

                ));
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(){

        //CompletableFuture.allOf().join();

        var id = Thread.currentThread().getId();
        var nameList = Arrays.asList(Thread.currentThread().getName().split("-"));

        var name = nameList.get(nameList.size()-1);

        System.out.println("Responding... "+ name + ":"+id);

        var m1 = Map.of(name,id);
        if(mapList.contains(m1)){
            System.out.println("DUPLICATE FOUND");
        }
        mapList.add(m1);


        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("""
                {"result":"this is from api A",
                "status":"%d",
                "method":"%s"}
                """.formatted(id,name));
    }



    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody Map<String,String> reqMap){

        myService.resetCount();

       return ResponseEntity.ok(myService.callService(reqMap));

    }




}
