package com.example.wss.mapper;

import com.example.wss.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {

    List<Customer> findAll();

    @Select("SELECT * FROM customer WHERE id = #{id}")
    Customer findCustomerById(Long id);
}
