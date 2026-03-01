package com.Ajay.Ecom_proj.Controller;


import com.Ajay.Ecom_proj.Model.Product;
import com.Ajay.Ecom_proj.Service.ProductService;
import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {

        Product Product = service.getProductById(id);
        if (Product != null)
            return new ResponseEntity<>(Product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImagedata();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }
    @PutMapping("product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
                                                 @RequestPart MultipartFile imageFile){
        Product product1 = null;
        try{
            product1 = service.Updateproduct(id, product, imageFile);
        }catch(IOException e){
            return new ResponseEntity<>("faild to update",HttpStatus.BAD_REQUEST);

        }
        if(product1 != null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("faild to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product != null){
            service.deleteProduct(id);
            return  new ResponseEntity<>("Delete", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchproducts(@RequestParam String keyword){
        System.out.println("serching with keyword" + keyword);
        List<Product> products = service.searchproducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);



    }

}