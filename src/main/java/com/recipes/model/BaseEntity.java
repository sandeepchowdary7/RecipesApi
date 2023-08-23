package com.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    @GeneratedValue
    @Id
    private Long id;
    @CreatedBy
    @JsonIgnore
    private String createdBy;
    @CreatedDate
    @JsonIgnore
    private Date creationDate;
    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy;
    @LastModifiedDate
    @JsonIgnore
    private Date lastModifiedDate;
}
