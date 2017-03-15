package dao;

import dao.Reservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T16:18:32")
@StaticMetamodel(Adherent.class)
public class Adherent_ { 

    public static volatile ListAttribute<Adherent, Reservation> reservationList;
    public static volatile SingularAttribute<Adherent, Integer> idAdherent;
    public static volatile SingularAttribute<Adherent, String> nomAdherent;
    public static volatile SingularAttribute<Adherent, String> prenomAdherent;

}