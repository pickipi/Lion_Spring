package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "students")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // school_id는 School과 관련된 필드이므로 먼저 인스턴스를 선언
    // Student가 School을 이처럼 가지도록 명시해줌으로써 관계를 자동매핑한다.
    // 학생 = Many, 학교 = One (일 대 다 관계) (이 클래스를 기준으로 첫번째 위치)
    // JoinColumn : 어떤 컬럼으로 해당 엔티티에 접근 중인지를 명시 (name = "school_id")
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Student(String name, School school) {
        this.name = name;
        this.school = school;
    }
}
