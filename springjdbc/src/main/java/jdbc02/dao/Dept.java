package jdbc02.dao;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class Dept {
    private Long id;
    private String deptName;
    private String location;
}
