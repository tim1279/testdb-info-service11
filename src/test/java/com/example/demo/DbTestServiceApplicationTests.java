//package com.example.demo;
//
//import lombok.RequiredArgsConstructor;
//import org.aspectj.weaver.ast.Call;
//import org.junit.jupiter.api.Test;
//import org.springframework.data.relational.core.sql.In;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@RequiredArgsConstructor
//class DbTestServiceApplicationTests {
//
//    RestTemplate restTemplate = new RestTemplate();
//    List<Long> listLongs = new CopyOnWriteArrayList<>();
//    @Test
//    void contextLoads() throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
////        List.of(Callable )
//        Callable<Info> callable0 = this::getInfo;
//        Callable<Info> callable1 = this::getInfo;
//        Callable<Info> callable2 = this::getInfo;
//        Callable<Info> callable3 = this::getInfo;
//        Callable<Info> callable4 = this::getInfo;
//        List<Callable<Info>> list = List.of(callable0, callable1, callable2, callable3, callable4);
//        executorService.invokeAll(list);
//        System.out.println(listLongs);
//    }
//
//    private Info getInfo() {
//        Info info = restTemplate.getForObject("http://localhost:8080/promo?userId=3", Info.class);
//        listLongs.add(info.getId());
//        return info;
//    }
//
//}
