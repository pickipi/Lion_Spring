package com.example.friendapp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Friend {
    @Id
    private Long id;
    private String name;
    private String email;
}
