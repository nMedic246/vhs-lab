package hr.truenorth.project.VHSRentalShop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="password")
    private String password;

    public User(String username, String name, String surname, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}
