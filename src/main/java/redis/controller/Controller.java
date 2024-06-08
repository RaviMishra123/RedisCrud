package redis.controller;

import redis.entity.Product;
import redis.pubSub.Publisher;
import redis.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class Controller {

    @Autowired
    private ProductService productService;
    @Autowired
    private Publisher publisher;

    @PostMapping("/product")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @PostMapping("/publish")
    public String pub(@RequestBody Product product){
        return publisher.publish(product);
    }

    @GetMapping("/product")
    public List<Product> getProduct(){
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductByIds(id);
    }

    @PostMapping("/product/{id}")
    public String deleteById(@PathVariable int id){
       return productService.deleteByIds(id);
    }
}
