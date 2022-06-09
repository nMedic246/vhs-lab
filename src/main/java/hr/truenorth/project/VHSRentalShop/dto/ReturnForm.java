package hr.truenorth.project.VHSRentalShop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ReturnForm {
    @NotNull(message = "{idVHS.not.empty}")
    private Long idVHS;
    @NotNull(message = "{username.not.empty}")
    private String username;
    @NotNull(message = "{returnDate.not.empty}")
    private Date returnDate;

    public ReturnForm(Long idVHS, String username, Date returnDate) {
        this.idVHS = idVHS;
        this.username = username;
        this.returnDate = returnDate;
    }
}
