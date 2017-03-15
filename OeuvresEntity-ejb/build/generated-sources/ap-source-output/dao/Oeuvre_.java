package dao;

import dao.Proprietaire;
import dao.Reservation;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T15:52:55")
@StaticMetamodel(Oeuvre.class)
public class Oeuvre_ { 

    public static volatile ListAttribute<Oeuvre, Reservation> reservationList;
    public static volatile SingularAttribute<Oeuvre, Proprietaire> proprietaire;
    public static volatile SingularAttribute<Oeuvre, BigDecimal> prix;
    public static volatile SingularAttribute<Oeuvre, String> titre;
    public static volatile SingularAttribute<Oeuvre, Integer> idOeuvre;

}