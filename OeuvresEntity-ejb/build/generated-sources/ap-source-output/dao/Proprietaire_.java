package dao;

import dao.Oeuvre;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T16:18:32")
@StaticMetamodel(Proprietaire.class)
public class Proprietaire_ { 

    public static volatile ListAttribute<Proprietaire, Oeuvre> oeuvreList;
    public static volatile SingularAttribute<Proprietaire, Integer> idProprietaire;
    public static volatile SingularAttribute<Proprietaire, String> login;
    public static volatile SingularAttribute<Proprietaire, String> pwd;
    public static volatile SingularAttribute<Proprietaire, String> nomProprietaire;
    public static volatile SingularAttribute<Proprietaire, String> prenomProprietaire;

}