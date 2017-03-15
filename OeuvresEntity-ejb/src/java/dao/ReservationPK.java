package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mélanie DUBREUIL et Ophélie EOUZAN
 * 
 */
@Embeddable
public class ReservationPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_reservation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReservation;

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_oeuvre")
    private int idOeuvre;

    public ReservationPK() {
    }

    public ReservationPK(Date dateReservation, int idOeuvre) {
        this.dateReservation = dateReservation;
        this.idOeuvre = idOeuvre;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateReservation != null ? dateReservation.hashCode() : 0);
        hash += (int) idOeuvre;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ReservationPK)) {
            return false;
        }
        ReservationPK other = (ReservationPK) object;
        if ((this.dateReservation == null && other.dateReservation != null) || (this.dateReservation != null && !this.dateReservation.equals(other.dateReservation))) {
            return false;
        }
        if (this.idOeuvre != other.idOeuvre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ReservationPK[ dateReservation=" + dateReservation + ", idOeuvre=" + idOeuvre + " ]";
    }
    
}
