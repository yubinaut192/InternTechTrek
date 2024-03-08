package com.example.TechTrekBackend.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/products/")
public class ProductController {
//    private ProductService productService;

//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("productByID/{id}")
    public Product getProductByID(@PathVariable("id") int id){
        System.out.println(id);
        return new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F);
    }

    @GetMapping("allProducts")
    public List<Product> getAllProducts(){
        return List.of(new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F));
    }

    @PostMapping("addProduct")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody Product product){
//        insert in db
        return new ResponseEntity<>("message: added product", HttpStatus.OK);
    }

    @GetMapping("test")
    public String test(){
        return "testing api";
    }
}