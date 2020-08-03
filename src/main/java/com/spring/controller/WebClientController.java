package com.spring.controller;

import com.spring.model.Employee;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@RestController
@RequestMapping("/emp/")
public class WebClientController {
    private RestTemplate template=new RestTemplate();

    @GetMapping()
    public ResponseEntity<Employee> getData() {
        String url = "http://localhost:9090/employees/";
        return template.exchange(url, HttpMethod.GET, null, Employee.class);
    }

    @GetMapping("/flux")
    public String getFlux()
    {
        WebClient webClient = WebClient.create("http://localhost:9090");
        System.out.println("client log**********");
        Mono<Employee> mono = webClient.get().uri("/employees/").retrieve().bodyToMono(Employee.class);
        System.out.println("client log1**********");

       //  System.out.println(mono.block());
       mono.subscribe(consumer);

        System.out.println("returning");
        return "SUCCESS";
    }

    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:9090");
        System.out.println("client log**********");
        Flux<Employee> mono = webClient.get().uri("/employees/").retrieve().bodyToFlux(Employee.class);
        System.out.println("client log1**********");

     //   System.out.println(mono.block());
        mono.subscribe(consumer);
        System.out.println("main done");
    }

    private static Consumer<Employee> consumer = (e)->{
        System.out.println("consumer consuming ===========");
        System.out.println(e);
    };
}
