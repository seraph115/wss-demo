package com.example.wss.controller;

import com.example.wss.model.Customer;
import com.example.wss.service.CustomerService;
import com.example.wss.websocket.CustomerWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerWebSocketHandler customerWebSocketHandler;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerWebSocketHandler customerWebSocketHandler) {
        this.customerService = customerService;
        this.customerWebSocketHandler = customerWebSocketHandler;
    }

    @PostMapping("/push")
    public void receiveAndPushCustomerId(@RequestBody Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            customerWebSocketHandler.sendCustomerToClient(customerId, customer);
        }
    }

    // 添加一个异常处理方法来捕获请求体为空的情况
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // 返回包含中文提示的错误消息
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求体不能为空，请提供'ID'有效入参");
    }
}




