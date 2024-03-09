//package com.example.TechTrekBackend.Product;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@Service
//public class ProductService {
//    @GetMapping("productByID/{id}")
//    public Product getProductByID(@PathVariable("id") int id){
//        System.out.println(id);
//        return new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F);
//    }
//    @GetMapping("allProducts")
//    public List<Product> getAllProducts(){
//        return List.of(new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F));
//    }
//    @GetMapping("test")
//    public String test(){
//        return "testing api";
//    }
//}
