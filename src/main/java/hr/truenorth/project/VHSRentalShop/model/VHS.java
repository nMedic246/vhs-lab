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
public class VHS {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name="id")
    private long id;

    @NotNull(message = "{VHS.name.not.empty}")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VHS vhs = (VHS) o;

        if (id != vhs.id) return false;
        if (yearPublished != vhs.yearPublished) return false;
        if (!name.equals(vhs.name)) return false;
        return duration.equals(vhs.duration);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + duration.hashCode();
        result = 31 * result + yearPublished;
        return result;
    }

    @Override
    public String toString() {
        return "VHS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", yearPublished=" + yearPublished +
                '}';
    }
}
