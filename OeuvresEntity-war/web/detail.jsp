<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>${titre}</h1>
    <br />
    <table class="table table-bordered table-striped" align="center">
        <thead>
            <tr>
                <td>Titre</td>
                <td>Prix </td>
                <td>Nom propriétaire</td>
                <td>Prénom propriétaire</td>        
            </tr>  
        </thead>
        <tbody>
            <tr>
                <td>${oeuvreR.titre}</td>
                <td>${oeuvreR.prix}</td>
                <td>${oeuvreR.proprietaire.nomProprietaire}</td>
                <td>${oeuvreR.proprietaire.prenomProprietaire}</td>                      
            </tr>
        </tbody>
    </table>
    <a class="btn btn-primary col-md-2 col-md-offset-5" href="catalogue.oe?">Retour</a>
</div>
