package com.Ajay.Ecom_proj.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jspecify.annotations.Nullable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String brand;

    private String description;

    private String category;

    private Double price;

    private Integer quantity;

    private Boolean available;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String imageName;

    private String imageType;

    @Lob
    private byte[] imageData;


    public byte[] getImagedata() {
        return imageData;
    }
}