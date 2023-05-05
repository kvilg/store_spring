package com.example.demo.repo;

import com.example.demo.model.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepo<T extends Product> extends CrudRepository<T,Long> {

}
