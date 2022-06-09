package hr.truenorth.project.VHSRentalShop.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RentalShopErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    public RentalShopErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
