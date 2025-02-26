package com.example.restexam.builderexam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Builder
public class Pizza {
    private String size;
    private boolean cheese;
    private boolean onion;
    private boolean potato;
}
