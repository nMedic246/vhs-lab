package hr.truenorth.project.VHSRentalShop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RentalForm {

    @NotNull(message = "{idVHS.not.empty}")
    private Long idVHS;
    @NotNull(message = "{username.not.empty}")
    private String username;
    @NotNull(message = "{rentalDate.not.empty}")
    private Date rentalDate;

    public RentalForm(Long idVHS, String username, Date rentalDate) {
        this.idVHS = idVHS;
        this.username = username;
        this.rentalDate = rentalDate;
    }
}
