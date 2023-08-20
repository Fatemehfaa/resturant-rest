package com.neshan.resturantrest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private String id;
    private String ownerId;
    private String name;
    private Map<String, Food> foods;

}
