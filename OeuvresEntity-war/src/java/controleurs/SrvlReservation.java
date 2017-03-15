package controleurs;

import dao.Adherent;
import dao.Reservation;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import outils.*;
import session.OeuvreFacade;
import session.ReservationFacade;
import session.ReservationPKFacade;
import dao.Oeuvre;
import dao.ReservationPK;
import session.AdherentFacade;

/**
 *
 * @author Mélanie DUBREUIL et Ophélie EOUZAN
 * 
 */

public class SrvlReservation extends HttpServlet {
   
    @EJB
    private ReservationFacade reservationF;
    
    @EJB
    private OeuvreFacade oeuvreF;
    
    @EJB
    private AdherentFacade adherentF;

    @EJB
    private ReservationPKFacade reservationPkF;
    private String erreur = "";

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String demande;
        String vueReponse = "/home.jsp";
        erreur = "";
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("reserver.res")) {
                vueReponse = reserverOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrerReservation.res")) {
                vueReponse = enregistrerReservation(request);
            } else if (demande.equalsIgnoreCase("listeReservations.res")) {
                vueReponse = listeReservations(request);
            } else if (demande.equalsIgnoreCase("confirmerReservation.res")) {
                vueReponse = confirmerReservation(request);
            } else if (demande.equalsIgnoreCase("supprimerReservation.res")) {
                vueReponse = supprimerReservation(request);
            }
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse); 
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".res"))
                dsp = request.getRequestDispatcher(vueReponse);
            dsp.forward(request, response);
        }
    }

    /**
     * Transforme dans la base de données une réservation en Attente
     * en une réservation Confirmée
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String confirmerReservation(HttpServletRequest request) throws Exception {
        Reservation reservation;
        String id, date;
        int id_oeuvre;
        try {
            reservation = new Reservation();
            id = request.getParameter("id");
            id_oeuvre = Integer.parseInt(id);
            date = request.getParameter("dateres").replace("'", "");
            //reservation.setId_oeuvre(id_oeuvre);
            java.util.Date dateRes = Utilitaire.StrToDate(date, "yyyy-MM-dd");
            //reservation.setDate_reservation(dateRes);
            //reservation.modifier();
            return ("listeReservations.res");
        } catch (Exception e) {
            throw e;
        }
    }

    private String supprimerReservation(HttpServletRequest request) throws Exception {
        String id, date;
        int id_oeuvre;
        try {
            Reservation reservation = new Reservation();
            id = request.getParameter("id");
            id_oeuvre = Integer.parseInt(id);
            date = request.getParameter("dateres").replace("'", "");
            //reservation.setId_oeuvre(id_oeuvre);
            java.util.Date dateRes = Utilitaire.StrToDate(date, "yyyy-MM-dd");
            //reservation.setDate_reservation(dateRes);
            //reservation.supprimer();
            return ("listeReservations.res");
        } catch (Exception e) {         
            throw e;
        }
    }     
    
    /**
     * liste des réservations en Attente
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String listeReservations(HttpServletRequest request) throws Exception {
        try {
            List<Reservation> reservationE = reservationF.getReservations();
            request.setAttribute("lstReservationsR", reservationE);
            request.setAttribute("titre","Liste des réservations");
            return ("/listereservations.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Enregistre une réservation et la met en Attente
     * @param request
     * @return
     * @throws Exception
     */
    private String enregistrerReservation(HttpServletRequest request) throws Exception {
        Reservation reservation;
        //Oeuvre oeuvre;
        String idOeuvre, idAdherent, date, titre;
        date = titre = "";

        try {
            reservation = new Reservation();
            int id_oeuvre = Integer.parseInt(request.getParameter("id"));
            int id_adherent = Integer.parseInt(request.getParameter("lstAdherents"));
            reservationF.Ajouter_Reservation(id_oeuvre, id_adherent, Utilitaire.StrToDate(request.getParameter("txtDate"), "yyyy-MM-dd"));

            return ("listeReservations.res");
        } catch (Exception e) {
            erreur = e.getMessage();
            if(erreur.contains("PRIMARY"))
                erreur = "L'oeuvre " + titre + " a déjà été réservée pour le : " + date + " !";            
            throw new Exception(erreur);
        }
    }

    /**
     * Lit une oeuvre, l'affiche et initialise la liste des adhérents
     * pour pouvoir saisir une réservation :
     * Saisie date et sélection de l'adhérent
     * @param request
     * @return
     * @throws Exception
     */
    private String reserverOeuvre(HttpServletRequest request) throws Exception {
        try {
            int id_oeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.Lire_Oeuvre_Id(id_oeuvre);
            List<Adherent> lAdherentsE = adherentF.Liste_Adherents();
//            request.setAttribute("titre", "Consulter/Modifier une oeuvre");
            request.setAttribute("oeuvreR", oeuvreE);
            request.setAttribute("lstAdherentsR", lAdherentsE);
            return ("/reservation.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Extrait le texte de la demande de l'URL
     * @param request
     * @return String texte de la demande
     */
    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
