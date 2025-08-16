
package com.example.tinyurl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Url {

    @Id
    @GeneratedValue
    private long id;
    private String originalUrl;
    private String shortUrl;

}
