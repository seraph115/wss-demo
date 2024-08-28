package com.example.wss.service;

import com.example.wss.model.Customer;
import com.example.wss.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public List<Customer> getAllCustomers() {
        return customerMapper.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerMapper.findCustomerById(id);
    }

    // 其他业务逻辑方法可以在此添加
}