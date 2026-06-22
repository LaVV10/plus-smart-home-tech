package ru.yandex.practicum.analyzer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "actions")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Column(name = "action_value")
    private Integer value;

    public Action(String type, Integer value) {
        this.type = type;
        this.value = value;
    }
}
