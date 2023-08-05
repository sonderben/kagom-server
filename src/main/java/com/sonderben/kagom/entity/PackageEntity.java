package com.sonderben.kagom.entity;

import com.sonderben.kagom.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageEntity extends BaseEntity {

    private String name;
    private String KMIdentity;
    private String description;
    private String content;
    private double weight;
    private double price;
    private double itbis;
    private int flammabilityLevel;
    private String dimension;
    private String codeBar;
    private String images;


    public static PackageEntity getExemple(){
        Random random = new Random();
        return PackageEntity.builder()
                .KMIdentity("943243434"+random.nextInt(10))
                .description("some descriptions: "+random.nextInt(10))
                .images("www.aws-s3/chien/"+random.nextInt(20))
                .content("cage for dog")
                .itbis(12.5)
                .name("name: "+random.nextInt(10))
                .weight(random.nextDouble(23.4f))
                .price(123.54)
                .flammabilityLevel(random.nextInt(5))
                .dimension("2*4*8")
                .codeBar(String.valueOf(random.nextLong(234533343)))
                .build();
    }

}
