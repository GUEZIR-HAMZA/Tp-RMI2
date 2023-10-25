/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import config.Config;
import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lachgar
 */
public class MachinesForm extends javax.swing.JInternalFrame {

    IDao<Machine> daoMachine = null;
    DefaultTableModel model = null;
    IDao<Salle> daoSalle=null;
    int column = 0;
    int id=0;
    /**
     * Creates new form MachineForm
     */
    public MachinesForm() {
        try {
            initComponents();
            model = (DefaultTableModel) tbl_machines.getModel();
            daoMachine = (IDao<Machine>) Naming.lookup("rmi://localhost:1099/Machine");
            daoSalle = (IDao<Salle>) Naming.lookup("rmi://localhost:1099/Salle");
            loadCombo();
            load();
        } catch (NotBoundException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadCombo(){
        try {
            for(Salle s : daoSalle.findAll())
                combo_salles.addItem(s);
        } catch (RemoteException ex) {
            Logger.getLogger(MachineBySalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void load(){
        try {
            model.setRowCount(0);
            for(Machine m : daoMachine.findAll())
                model.addRow(new Object[]{
                    m.getId(),
                    m.getRef(),
                    m.getMarque(),
                    m.getPrix(),
                    m.getSalle()
                        
                });
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
      public void fillForm(){
         try {
            // TODO add your handling code here:

            Machine m= daoMachine.findById(id);
            
            txt_ref.setText(m.getRef());
            txt_marque.setText(m.getMarque());
            txt_prix.setText(m.getPrix()+"");
            for(int i=1;i< combo_salles.getItemCount();i++){
                if(m.getSalle().getId() == ((Salle) combo_salles.getItemAt(i)).getId()){
                    System.out.println((Salle) combo_salles.getItemAt(i));
                    combo_salles.setSelectedIndex(i);
                }
            }
            
       } catch (RemoteException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
       public void clearForm(){
        txt_ref.setText("");
            txt_marque.setText("");
            txt_prix.setText("");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_ref = new javax.swing.JTextField();
        txt_marque = new javax.swing.JTextField();
        txt_prix = new javax.swing.JTextField();
        combo_salles = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_ajouter = new javax.swing.JButton();
        btn_supprimer = new javax.swing.JButton();
        btn_modifier = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_machines = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("G Machine");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations Machines"));

        jLabel1.setText("Réf      : ");

        jLabel2.setText("Marque : ");

        jLabel3.setText("Prix    : ");

        combo_salles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combo_salles.setAutoscrolls(true);

        jLabel4.setText("Salle   :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo_salles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ref)
                    .addComponent(txt_marque)
                    .addComponent(txt_prix, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_marque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_prix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_salles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));

        btn_ajouter.setText("Ajouter");
        btn_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajouterActionPerformed(evt);
            }
        });

        btn_supprimer.setText("Supprimer");
        btn_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supprimerActionPerformed(evt);
            }
        });

        btn_modifier.setText("Modifier");
        btn_modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modifierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_ajouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_supprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_modifier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btn_ajouter)
                .addGap(18, 18, 18)
                .addComponent(btn_modifier)
                .addGap(18, 18, 18)
                .addComponent(btn_supprimer)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Liste des machines"));

        tbl_machines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Réf", "Marque", "Prix", "Salle"
            }
        ));
        tbl_machines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_machinesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_machines);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajouterActionPerformed
        try {
            // TODO add your handling code here:
            String ref = txt_ref.getText().toString();
            String marque = txt_marque.getText().toString();
            double prix = txt_prix.getText().isEmpty() ? 0:Double.parseDouble(txt_prix.getText().toString());
            
            Salle s=(Salle) combo_salles.getSelectedItem();
            
            if(ref=="" || marque.isEmpty() || s == null){
                 JOptionPane.showMessageDialog(this, "Merci de remplir les champs !", "Dialog",
        JOptionPane.ERROR_MESSAGE);
                
            }else{
            daoMachine.create(new Machine(ref, marque, prix,s));
            load();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(this, "Machine ajouter avec succès", "Dialog",
        JOptionPane.INFORMATION_MESSAGE);
           clearForm();
            load();
    }//GEN-LAST:event_btn_ajouterActionPerformed

    private void btn_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprimerActionPerformed
  try {
            daoMachine.delete(daoMachine.findById(id));
            
            
// TODO add your handling code here:
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
   JOptionPane.showMessageDialog(this, "Machine supprimer avec succès", "Dialog",
        JOptionPane.INFORMATION_MESSAGE);
           clearForm();
        load();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_supprimerActionPerformed

    private void btn_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modifierActionPerformed
        // TODO add your handling code here:
             try {
            // TODO add your handling code here:

             Machine m=daoMachine.findById(id);
            
            m.setMarque(txt_marque.getText());
            m.setPrix(Double.parseDouble(txt_prix.getText()));
            m.setRef(txt_ref.getText());
            Salle s=(Salle) combo_salles.getSelectedItem();
             if(m.getRef()=="" || m.getMarque().isEmpty() || s == null){
                 JOptionPane.showMessageDialog(this, "Merci de remplire les champ!", "Dialog",
        JOptionPane.ERROR_MESSAGE);
                
            }else{
                 m.setSalle(s);
            daoMachine.update(m);
             }
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
                  clearForm();
        load();  
             
    }//GEN-LAST:event_btn_modifierActionPerformed

    private void tbl_machinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_machinesMouseClicked
        // TODO add your handling code here:
        
           int row = tbl_machines.getSelectedRow();
     id = Integer.parseInt( tbl_machines.getModel().getValueAt(row, column).toString()); 
     
     
     fillForm();
    }//GEN-LAST:event_tbl_machinesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ajouter;
    private javax.swing.JButton btn_modifier;
    private javax.swing.JButton btn_supprimer;
    private javax.swing.JComboBox combo_salles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_machines;
    private javax.swing.JTextField txt_marque;
    private javax.swing.JTextField txt_prix;
    private javax.swing.JTextField txt_ref;
    // End of variables declaration//GEN-END:variables
}
