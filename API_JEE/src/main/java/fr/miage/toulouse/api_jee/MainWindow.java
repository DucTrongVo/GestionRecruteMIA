/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.api_jee;

import fr.miage.toulouse.projetjee.projetjeeshared.CompetenceShared;
import fr.miage.toulouse.projetjee.projetjeeshared.PersonneShared;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;
import services.ServiceGestionRHRemote;

/**
 *
 * @author trongvo
 */
public class MainWindow extends javax.swing.JFrame {
    private final String[] Role = {
        "Collaborateur",
        "Codir",
        "Manager",
        "Candidata",
    };
    final ServiceGestionRHRemote serviceRH;
    DefaultTableModel collabTableModel;

    /**
     * Creates new form MainWindow
     * @param serviceRH service gestion RH
     */
    public MainWindow(ServiceGestionRHRemote serviceRH) {
        initComponents();
        this.serviceRH = serviceRH;
        collabTableModel = (DefaultTableModel) collabTable.getModel();
        try{
            List<CompetenceShared> listCompetences = new ArrayList<>();
            CompetenceShared cs = serviceRH.creerCompetence("Java");
            System.out.println("COMPETENCE CREATE SUCCES "+cs.getNom());
            listCompetences.add(cs);
//            System.out.println("ADDED "+listCompetences.get(0).getNom());
//            listCompetences.add(serviceRH.creerCompetence("Flutter"));
//            System.out.println("ADDED "+listCompetences.get(0).getNom());
//            System.out.println("COMPETENCE SHARED size"+listCompetences.size());
//            System.out.println("COMPETENCE SHARED "+listCompetences.get(0).getNom());
            //PersonneShared lamine = serviceRH.creerPersonneSiInexistant("Lamine", "Boutaleb", listCompetences);
            //PersonneShared lamine = serviceRH.creerPersonneSiInexistant("Lamine", "Boutaleb", listCompetences);
            PersonneShared maxime = serviceRH.creerPersonneSiInexistant("Maxime", "Bertrou-Rivot", (ArrayList<CompetenceShared>) listCompetences);
            serviceRH.creerEquipe("MIAGE", maxime.getNom(), maxime.getPrenom() );
            this.chargerCollaborateur();
        }catch(Exception e){
            System.out.println("Error : "+e.toString());
        }     
    }
    
    public void chargerCollaborateur(){
        try{
            List<PersonneShared> listCollab = serviceRH.getListCollaborateur();
            listCollab.stream().map((PersonneShared personne) -> {
                String role;
                if(personne.getEquipe() == null){
                    role = Role[3];
                }else{
                    role = Role[0];
                    role += personne.isIsCodir() ? " "+Role[1] : "";
                    role += personne.isIsManager()? " "+Role[2] : "";
                }
                String[] line = {personne.getNom(), personne.getPrenom(), role};
                return line;
                }).forEachOrdered((line) -> {
                    collabTableModel.addRow(line);
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
        creerDemandePoste = new javax.swing.JPanel();

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

        javax.swing.GroupLayout listPostsOuvertsLayout = new javax.swing.GroupLayout(listPostsOuverts);
        listPostsOuverts.setLayout(listPostsOuvertsLayout);
        listPostsOuvertsLayout.setHorizontalGroup(
            listPostsOuvertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        listPostsOuvertsLayout.setVerticalGroup(
            listPostsOuvertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        creerDemandePosteTab.addTab("Liste Postes Ouverts", listPostsOuverts);

        javax.swing.GroupLayout creerDemandePosteLayout = new javax.swing.GroupLayout(creerDemandePoste);
        creerDemandePoste.setLayout(creerDemandePosteLayout);
        creerDemandePosteLayout.setHorizontalGroup(
            creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        creerDemandePosteLayout.setVerticalGroup(
            creerDemandePosteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
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
    private javax.swing.JTable collabTable;
    private javax.swing.JPanel creerDemandePoste;
    private javax.swing.JTabbedPane creerDemandePosteTab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel listCollab;
    private javax.swing.JPanel listPostsOuverts;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables
}
