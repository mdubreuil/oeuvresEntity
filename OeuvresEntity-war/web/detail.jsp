<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>${titre}</h1>
    <table class="table table-bordered table-striped">
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
</div>
