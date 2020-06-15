/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.api_jee;

import fr.miage.toulouse.projetjee.projetjeeshared.CompetenceShared;
import fr.miage.toulouse.projetjee.projetjeeshared.EquipeShared;
import fr.miage.toulouse.projetjee.projetjeeshared.FicheDePosteShared;
import fr.miage.toulouse.projetjee.projetjeeshared.PersonneShared;
import fr.miage.toulouse.projetjee.projetjeeshared.StatutDePoste;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import services.ServiceGestionRHRemote;

/**
 *
 * @author trongvo
 */
public class MainWindow extends javax.swing.JFrame {
    private List<CompetenceShared> listCompetences;
    private List<PersonneShared> listPersonnes;
    private List<EquipeShared> listEquipes;
    private List<FicheDePosteShared> listPostes;
    //private EquipeShared equipeChoisi;
    //private List<CompetenceShared> listCompetencesChoisi;
    private final String[] Role = {
        "Collaborateur",
        "Codir",
        "Manager",
    };
    final ServiceGestionRHRemote serviceRH;
    DefaultTableModel collabTableModel;
    DefaultTableModel fdpTableModel;
    //DefaultListModel listCompetenceModel;

    /**
     * Creates new form MainWindow
     * @param serviceRH service gestion RH
     */
    public MainWindow(ServiceGestionRHRemote serviceRH) {
        initComponents();
        this.serviceRH = serviceRH;
        collabTableModel = (DefaultTableModel) collabTable.getModel();
        fdpTableModel = (DefaultTableModel) tableauFDP.getModel();
        jListCompetences.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listCompetences = new ArrayList<>();
        listPersonnes = new ArrayList<>();
        listEquipes = new ArrayList<>();
        listPostes = new ArrayList<>();
        //equipeChoisi = null;
        this.initialeValue();
    }
    public void initialeValue(){
        try{
            // create competence
            listCompetences.add(serviceRH.creerCompetence("Java"));
            listCompetences.add(serviceRH.creerCompetence("Flutter"));
            
            // create collab
            PersonneShared laminus = serviceRH.creerPersonneSiInexistant("Lamine", "Boutaleb", (ArrayList<CompetenceShared>) listCompetences);
            listPersonnes.add(laminus);
            PersonneShared maxime = serviceRH.creerPersonneSiInexistant("Maxime", "Bertrou-Rivot", (ArrayList<CompetenceShared>) listCompetences);
            listPersonnes.add(maxime);
            PersonneShared trong = serviceRH.creerPersonneSiInexistant("Trong", "VO", (ArrayList<CompetenceShared>) listCompetences);
            listPersonnes.add(trong);
            serviceRH.setCodir(trong);
            trong.setIsCodir(true);
            System.out.println("TRONG IS NOW CODIR "+trong.isIsCodir());
            
            // create equipe
            EquipeShared equipe1 = serviceRH.creerEquipe("MIAGE", maxime.getNom(), maxime.getPrenom() );
            listEquipes.add(equipe1);
            ArrayList<String> nomCompetence = new ArrayList<>(Arrays.asList(listCompetences.get(0).getNom()));
            FicheDePosteShared fdps = serviceRH.creerFicheDePosteDeDemande("Chercher Java DEV", nomCompetence, listEquipes.get(0).getNom());
            System.out.println("POST CREATED "+fdps.getNom());
            serviceRH.validerLaCreationUnPoste(trong.getId(), fdps.getId(), fdps.getPresentationEntreprise(), "Une poste test");
            fdps.setStatut(StatutDePoste.OUVERT);
            System.out.println("POST VALIDATED "+fdps.getNom());
            listPostes.add(fdps);
            
            // charger tableau
            this.chargerCollaborateur();
            fillListCompetence();
            fillListEquipe();
            chargerLesPostesOuverts();
        }catch(Exception e){
            System.out.println("Error : "+e.toString());
        } 
    }
    public void chargerCollaborateur(){
        try{
            List<PersonneShared> listCollab = serviceRH.getListCollaborateur();
            listCollab.stream().map((PersonneShared personne) -> {
                String role = Role[0];
                role += personne.isIsCodir() ? " ; "+Role[1] : "";
                role += personne.isIsManager() ? " ; "+Role[2] : "";
                
                String[] line = {personne.getNom(), personne.getPrenom(), role};
                return line;
                }).forEachOrdered((line) -> {
                    collabTableModel.addRow(line);
            });
        }catch(Exception e){
            System.out.println("Error : "+e.toString());
        }
    }
    public void fillListCompetence(){
        String[] data = new String[listCompetences.size()];
        for(int i=0; i<listCompetences.size(); i++){
            data[i] = listCompetences.get(i).getNom();
           //listCompetenceBox.addItem(listCompetences.get(i).getNom());
        }
        this.jListCompetences.setListData(data);
    }
    public void fillListEquipe(){
        for(EquipeShared es : listEquipes){
            listEquipeBox.addItem(es.getNom());
        }
        
    }
    public void chargerLesPostesOuverts(){
        try{
            List<FicheDePosteShared> listFicheDePoste = serviceRH.getAllOpenedPoste();
            listFicheDePoste.stream().map((FicheDePosteShared ficheDePoste) -> {
                String[] line = {ficheDePoste.getNom(), ficheDePoste.getPresentationPoste(), ficheDePoste.getEquipeDemandeuse().getNom(),ficheDePoste.getPresentationEntreprise()};
                return line;
                }).forEachOrdered((line) -> {
                    fdpTableModel.addRow(line);
            });
        }catch(Exception e){
            System.out.println("Error : "+e.toString());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titre = new javax.swing.JLabel();
        creerDemandePosteTab = new javax.swing.JTabbedPane();
        listCollab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        collabTable = new javax.swing.JTable();
        listPostsOuverts = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableauFDP = new javax.swing.JTable();
        creerDemandePoste = new javax.swing.JPanel();
        nomFicheDePoste = new javax.swing.JLabel();
        nomFicheDePosteText = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListCompetences = new javax.swing.JList<>();
        label3 = new javax.swing.JLabel();
        BttDemander = new javax.swing.JToggleButton();
        listEquipeBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titre.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre.setText("Gestion RH");
        titre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        collabTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Rôle"
            }
        ));
        jScrollPane1.setViewportView(collabTable);

        javax.swing.GroupLayout listCollabLayout = new javax.swing.GroupLayout(listCollab);
        listCollab.setLayout(listCollabLayout);
        listCollabLayout.setHorizontalGroup(
            listCollabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        listCollabLayout.setVerticalGroup(
            listCollabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listCollabLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        creerDemandePosteTab.addTab("Liste Collaborateur", listCollab);

        tableauFDP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Presentation Poste", "Equipe Demandeuse", "Presentation Entreprise"
            }
        ));
        jScrollPane3.setViewportView(tableauFDP);

        javax.swing.GroupLayout listPostsOuvertsLayout = new javax.swing.GroupLayout(listPostsOuverts);
        listPostsOuverts.setLayout(listPostsOuvertsLayout);
        listPostsOuvertsLayout.setHorizontalGroup(
            listPostsOuvertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listPostsOuvertsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                .addContainerGap())
        );
        listPostsOuvertsLayout.setVerticalGroup(
            listPostsOuvertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listPostsOuvertsLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        creerDemandePosteTab.addTab("Liste Postes Ouverts", listPostsOuverts);

        nomFicheDePoste.setText("Nom de la Fiche de Poste :");
        nomFicheDePoste.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        label2.setText("Choisir les compétences :");
        label2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jScrollPane2.setViewportView(jListCompetences);

        label3.setText("Choisir l'équipe demandeuse");
        label3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        BttDemander.setText("FAIRE LA DEMANDE DE FICHE DE POSTE");
        BttDemander.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttDemanderActionPerformed(evt);
            }
        });

        listEquipeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listEquipeBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout creerDemandePosteLayout = new javax.swing.GroupLayout(creerDemandePoste);
        creerDemandePoste.setLayout(creerDemandePosteLayout);
        creerDemandePosteLayout.setHorizontalGroup(
            creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creerDemandePosteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(creerDemandePosteLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(creerDemandePosteLayout.createSequentialGroup()
                                    .addComponent(label3)
                                    .addGap(18, 18, 18)
                                    .addComponent(listEquipeBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(creerDemandePosteLayout.createSequentialGroup()
                                    .addComponent(nomFicheDePoste)
                                    .addGap(38, 38, 38)
                                    .addComponent(nomFicheDePosteText, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(creerDemandePosteLayout.createSequentialGroup()
                                .addComponent(label2)
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(BttDemander, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(370, Short.MAX_VALUE))
        );
        creerDemandePosteLayout.setVerticalGroup(
            creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creerDemandePosteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomFicheDePoste)
                    .addComponent(nomFicheDePosteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2))
                .addGap(34, 34, 34)
                .addGroup(creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3)
                    .addComponent(listEquipeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(BttDemander)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        creerDemandePosteTab.addTab("Creer Demande Poste", creerDemandePoste);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creerDemandePosteTab)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(creerDemandePosteTab)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listEquipeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listEquipeBoxActionPerformed
       String nomChoisi = (String)listEquipeBox.getSelectedItem();
    }//GEN-LAST:event_listEquipeBoxActionPerformed

    private void BttDemanderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttDemanderActionPerformed
        serviceRH.creerFicheDePosteDeDemande(nomFicheDePosteText.getText(), (ArrayList<String>)jListCompetences.getSelectedValuesList(), (String)listEquipeBox.getSelectedItem());
    }//GEN-LAST:event_BttDemanderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws NamingException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//        Context ic = new InitialContext();
//        ServiceGestionRHRemote serviceRH = (ServiceGestionRHRemote) ic.lookup("services.ServiceGestionRHRemote");
        
        //Contexte de nommage pour l'accès à l'annuaire JNDI du serveur Glassfish
        Properties jNDIProperties = new Properties();
        jNDIProperties.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext namingContext = new InitialContext(jNDIProperties);
        //Récupération d'un accès à l'EJB distant
        ServiceGestionRHRemote serviceRH = (ServiceGestionRHRemote) namingContext.lookup("services.ServiceGestionRHRemote");
        
        //java:global/ProjetJEE-ear/ProjetJEE-ejb-1.0/ServiceCandidat!services.ServiceCandidatRemote
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow(serviceRH).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BttDemander;
    private javax.swing.JTable collabTable;
    private javax.swing.JPanel creerDemandePoste;
    private javax.swing.JTabbedPane creerDemandePosteTab;
    private javax.swing.JList<String> jListCompetences;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JPanel listCollab;
    private javax.swing.JComboBox<String> listEquipeBox;
    private javax.swing.JPanel listPostsOuverts;
    private javax.swing.JLabel nomFicheDePoste;
    private javax.swing.JTextField nomFicheDePosteText;
    private javax.swing.JTable tableauFDP;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables
}
