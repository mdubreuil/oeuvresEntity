package controleurs;

import dao.Oeuvre;
import dao.Proprietaire;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.OeuvreFacade;
import session.ProprietaireFacade;

/**
 *
 * @author Mélanie DUBREUIL et Ophélie EOUZAN
 * 
 */
public class SrvlOeuvres extends HttpServlet {
    @EJB
    private OeuvreFacade oeuvreF;
    @EJB
    private ProprietaireFacade proprietaireF;
    private String erreur;

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
            if (demande.equalsIgnoreCase("ajouter.oe")) {
                vueReponse = creerOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrer.oe")) {
                vueReponse = enregistrerOeuvre(request);
            } else if (demande.equalsIgnoreCase("modifier.oe")) {
                vueReponse = modifierOeuvre(request);
            } else if (demande.equalsIgnoreCase("catalogue.oe")) {
                vueReponse = listerOeuvres(request);
            } else if (demande.equalsIgnoreCase("supprimer.oe")) {
                vueReponse = supprimerOeuvre(request);
            } else if (demande.equalsIgnoreCase("detail.oe")) {
                vueReponse = detailOeuvre(request);
            } 
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);            
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".oe"))
                dsp = request.getRequestDispatcher(vueReponse);
            dsp.forward(request, response);
        }
    }

    /**
     * Enregistre une oeuvre qui a été soit créée (id_oeuvre = 0)
     * soit modifiée (id_oeuvre > 0)
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String enregistrerOeuvre(HttpServletRequest request) throws Exception {
        String vueReponse, titre;
        int id_oeuvre, id_proprietaire;
        double prix;
        
        try {
            id_oeuvre = Integer.parseInt(request.getParameter("id"));
            titre = request.getParameter("txtTitre");
            prix = Double.parseDouble(request.getParameter("txtTitre"));
            id_proprietaire = Integer.parseInt(request.getParameter("lProprietaires"));
            if (id_oeuvre > 0) {
                oeuvreF.Modifier_Oeuvre(id_oeuvre,titre,prix,id_proprietaire);
            } else {
                oeuvreF.Ajouter_Oeuvre(id_oeuvre,titre,prix,id_proprietaire);
            }
            vueReponse = "catalogue.oe";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Affiche le formulaire vide d'une oeuvre
     * Initialise la liste des propriétaires
     * Initialise le titre de la page
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String creerOeuvre(HttpServletRequest request) throws Exception {
        // Constructeur mauvais ?
        Oeuvre oeuvreE = new Oeuvre();
        try {
            List <Proprietaire> lProprietairesE = proprietaireF.Liste_Proprietaires();
            request.setAttribute("oeuvreR", oeuvreE);
            request.setAttribute("lstProprietairesR", lProprietairesE);
            request.setAttribute("titre", "Créer une oeuvre");
            return "/oeuvre.jsp";
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Lit et affiche une oeuvre pour pouvoir la modifier
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String modifierOeuvre(HttpServletRequest request) throws Exception {
        try {
            int id_oeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.Lire_Oeuvre_Id(id_oeuvre);
            List <Proprietaire> lProprietairesE = proprietaireF.Liste_Proprietaires();
            request.setAttribute("oeuvreR", oeuvreE);
            request.setAttribute("lstProprietairesR", lProprietairesE);
            request.setAttribute("titre", "Consulter/Modifier une oeuvre");
            return ("/oeuvre.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Supprimer une oeuvre
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String supprimerOeuvre(HttpServletRequest request) throws Exception {
        String vueReponse;
        int id_oeuvre;
        try {
            id_oeuvre = Integer.parseInt(request.getParameter("id"));
            oeuvreF.Supprimer_Oeuvre_Id(id_oeuvre);
            vueReponse = "catalogue.oe";           
            return (vueReponse);  
        } catch (Exception e) {
            erreur = e.getMessage();
            /*if(erreur.contains("FK_RESERVATION_OEUVRE"))
                erreur = "Il n'est pas possible de supprimer l'oeuvre car elle a été réservée !";*/            
            throw new Exception(erreur);
        }
    }    
       
    /**
     * liste des oeuvres pour le catalogue
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String listerOeuvres(HttpServletRequest request) throws Exception {
        try {
            List<Oeuvre> oeuvreE = oeuvreF.Liste_Oeuvres();
            request.setAttribute("lstOeuvresR", oeuvreE);
            request.setAttribute("titre","Liste des oeuvres");
            return ("/catalogue.jsp");
        } catch (Exception e) {
            throw e;
        }
    }
    
    private String detailOeuvre(HttpServletRequest request) throws Exception {
        try {
            int id_oeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.Lire_Oeuvre_Id(id_oeuvre);
            request.setAttribute("oeuvreR", oeuvreE);
            List <Proprietaire> lProprietairesE = proprietaireF.Liste_Proprietaires();
            request.setAttribute("lstProprietairesR", lProprietairesE);
            request.setAttribute("titre", "Détail d'une oeuvre");
            return ("/oeuvre.jsp");
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
