package hr.truenorth.project.VHSRentalShop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.truenorth.project.VHSRentalShop.model.id.RentalId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="rental")
@Getter
@Setter
@NoArgsConstructor
@IdClass(RentalId.class)
public class Rental {

    @ManyToOne
    @NotNull(message = "{idVHS.not.null}")
    @JoinColumn(name="idVHS")
    @Id
    private VHS idVHS;

    @Column(name="rentalDate")
    @NotNull(message = "{rentalDate.not.null}")
    @Id
    private Date rentalDate;

    @ManyToOne
    @NotNull(message = "{user.not.null}")
    @JoinColumn(name = "username")
    private User user;

    @Column(name="dueDate")
    private Date dueDate;

    @Column(name="fee")
    private int fee;

    @Column(name="isReturned")
    private boolean isReturned;

    @Column(name="additionalFee")
    private Float additionalFee;

    public Rental(VHS idVHS, Date rentalDate, User user, Date dueDate, int fee, boolean isReturned, Float additionalFee) {
        this.idVHS = idVHS;
        this.rentalDate = rentalDate;
        this.user = user;
        this.dueDate = dueDate;
        this.fee = fee;
        this.isReturned = isReturned;
        this.additionalFee = additionalFee;
    }


}



