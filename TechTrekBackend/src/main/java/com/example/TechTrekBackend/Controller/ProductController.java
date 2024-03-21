package com.example.TechTrekBackend.Controller;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.example.TechTrekBackend.Product.Product;
import com.example.TechTrekBackend.Repository.ProductRepository;
import com.example.TechTrekBackend.Service.AWSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@RestController
@EnableCaching
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/v1/products/")
public class ProductController {
//    private ProductService productService;

//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    private final AWSService s3Service;

    public ProductController(AWSService s3Service) {
        this.s3Service = s3Service;
    }

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("productByID/{id}")
    public Optional<Product> getProductByID(@PathVariable("id") Long id){
        System.out.println(id);
        return productRepository.findById(id);
//        return new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F);
    }


    @Cacheable("Products")
    @GetMapping("allProducts")
    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
//        return List.of(new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F), new Product(21L,"chocolate",1,"a big bar of chocolate",1,90F));
    }

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @PostMapping(value = "addProduct")
    @Cacheable("Product")
    @ResponseBody
    public Product addProduct(@RequestParam(value = "imageFile", required = false) MultipartFile imageFile, @RequestParam("product") String productJson) {
        if (imageFile == null) {
            imageFile = null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);

            // You can access the properties of the product object here
            System.out.println("Product: " + product);
            System.out.println("Image: " + imageFile);

            if (imageFile != null) {
                // Upload image to AWS S3
                s3Service.uploadFile(imageFile.getOriginalFilename(), imageFile);

                // Generate a presigned URL for the image
                String keyName = imageFile.getOriginalFilename();
                GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, keyName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(new Date(System.currentTimeMillis() + 90000000));
                ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
                responseHeaders.setContentType(MediaType.IMAGE_JPEG_VALUE);
                generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
                String presignedURL = s3Service.s3client.generatePresignedUrl(generatePresignedUrlRequest).toString();

                System.out.println("Presigned URL: " + presignedURL);
                product.setImageURL(presignedURL);
            }

            // Save the product in the local database
            return productRepository.save(product);
        } catch (IOException e) {
            // Handle the exception if there is an error parsing the JSON
            e.printStackTrace();
            return null;
        }
    }

    
    @Cacheable("Product")
    @PostMapping("updateProduct/{id}")
    public ResponseEntity<Product> updateProductByID(@PathVariable("id") Long id, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, @RequestParam("product") String productJson) {
        if (imageFile == null) {
            imageFile = null; // Set default value to null if not present
        }
        // Rest of the code...
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product updatedProduct = objectMapper.readValue(productJson, Product.class);
            Optional<Product> optionalProduct = productRepository.findById(id);
            System.out.println(updatedProduct);
            if (optionalProduct.isPresent()) {
                Product existingProduct = optionalProduct.get();

                // Update fields with the new data
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setCategory_id(updatedProduct.getCategory_id());
                existingProduct.setDesc(updatedProduct.getDesc());
                existingProduct.setInventory_id(updatedProduct.getInventory_id());
                existingProduct.setPrice(updatedProduct.getPrice());
                System.out.println(imageFile);
                if (imageFile != null) {
                    // Upload image to AWS S3
                    s3Service.uploadFile(imageFile.getOriginalFilename(), imageFile);

                    // Generate a presigned URL for the image
                    String keyName = imageFile.getOriginalFilename();
                    GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, keyName)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(new Date(System.currentTimeMillis() + 90000000));
                    ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
                    responseHeaders.setContentType(MediaType.IMAGE_JPEG_VALUE);
                    generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
                    String presignedURL = s3Service.s3client.generatePresignedUrl(generatePresignedUrlRequest).toString();

                    System.out.println("Presigned URL: " + presignedURL);
                    existingProduct.setImageURL(presignedURL);
                }

                // Save the updated product to the database
                Product savedProduct = productRepository.save(existingProduct);

                return new ResponseEntity<>(savedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SdkClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
    }

    @Cacheable("Product")
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