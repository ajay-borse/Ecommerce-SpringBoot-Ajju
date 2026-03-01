package com.Ajay.Ecom_proj.Service;


import com.Ajay.Ecom_proj.Model.Product;
import com.Ajay.Ecom_proj.Repo.ProductRepo;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {

        return repo.findAll();

    }

    public Product getProductById(int id) {
        return repo.findById(id).get();
    }

    public Product addProduct(Product product, MultipartFile imageFile)throws IOException {
       product.setImageName(imageFile.getOriginalFilename());
       product.setImageType(imageFile.getContentType());
       product.setImageData(imageFile.getBytes());

       return repo.save(product);
    }


    public Product Updateproduct(int id, Product product, MultipartFile imageFile) throws IOException{
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);

    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchproducts(String keyword) {
        return repo.searchProducts(keyword);


    }
}

