package hr.truenorth.project.VHSRentalShop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="VHS")
@EqualsAndHashCode
@ToString
public class VHS {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name="id")
    private long id;

    @NotNull
    @Column(name="name")
    private String name;

    @Column(name="duration")
    private Time duration;

    @Column(name="yearPublished")
    private int yearPublished;


    public VHS(String name, Time duration,int year) {
        this.name = name;
        this.duration = duration;
        this.yearPublished = year;
    }
}
