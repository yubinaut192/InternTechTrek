package com.example.TechTrekBackend.Controller;

import com.example.TechTrekBackend.Product.Product;
import com.example.TechTrekBackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="api/v1/products/")
public class ProductController {
//    private ProductService productService;

//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("productByID/{id}")
    public Optional<Product> getProductByID(@PathVariable("id") Long id){
        System.out.println(id);
        return productRepository.findById(id);
//        return new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F);
    }


    @GetMapping("allProducts")
    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
//        return List.of(new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F));
    }

    @PostMapping(
            value="addProduct"
//            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public Product addProduct(@RequestBody Product product){
//        insert in db
        System.out.println(product);
//        Product test = new Product(21,"Dog Walker",1,"walker for dogs",1,1000);
        return productRepository.save(product);
//        return new ResponseEntity<>("message: added product", HttpStatus.OK);
    }

    @DeleteMapping("deleteByID/{id}")
    public ResponseEntity<String> deleteProductByID(@PathVariable("id") Long id){
        System.out.println(id);
        productRepository.deleteById(id);
        return new ResponseEntity<>("message: deleted product", HttpStatus.OK);
//        return new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F);
    }

    @GetMapping("test")
    public String test(){
        return "testing api";
    }
}