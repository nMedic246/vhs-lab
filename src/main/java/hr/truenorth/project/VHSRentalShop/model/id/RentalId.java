package hr.truenorth.project.VHSRentalShop.model.id;

import java.io.Serializable;
import java.util.Date;

public class RentalId implements Serializable {

    private Date rentalDate;
    private long idVHS;

    public RentalId(Date rentalDate, long idVHS) {
        this.rentalDate = rentalDate;
        this.idVHS = idVHS;
    }

    public RentalId() {
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public long getIdVHS() {
        return idVHS;
    }

    public void setIdVHS(long idVHS) {
        this.idVHS = idVHS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalId rentalId = (RentalId) o;

        if (idVHS != rentalId.idVHS) return false;
        return rentalDate != null ? rentalDate.equals(rentalId.rentalDate) : rentalId.rentalDate == null;
    }

    @Override
    public int hashCode() {
        int result = rentalDate != null ? rentalDate.hashCode() : 0;
        result = 31 * result + (int) (idVHS ^ (idVHS >>> 32));
        return result;
    }
}
