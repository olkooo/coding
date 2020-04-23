package com.verygood.security.coding.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "aliases")
@Data
public class Alias {
    @Id
    private String alias;
    private String data;
}
