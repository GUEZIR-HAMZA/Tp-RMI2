/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author grham
 */
public class MachinesParSalle extends javax.swing.JInternalFrame {

    /**
     * Creates new form SalleFilter
     */
    
    IDao<Machine> daoMachine = null;
    IDao<Salle> daoSalle=null;
    DefaultTableModel model = null;
    int column = 0;
    int SelectedMachineId = -1;
    
    public MachinesParSalle() {
        initComponents();
        
        model = (DefaultTableModel) tbl_machines.getModel();
        try {
            daoMachine = (IDao<Machine>) Naming.lookup("rmi://localhost:1099/Machine");
            daoSalle = (IDao<Salle>) Naming.lookup("rmi://localhost:1099/Salle");
            loadCombo();
        } catch (NotBoundException ex) {
            Logger.getLogger(MachinesParSalle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MachinesParSalle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesParSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadCombo(){
        try {
            combo_salles.removeAllItems();
            combo_salles.addItem("Select Salle :");
            for(Salle salle : daoSalle.findAll())
                combo_salles.addItem(salle);
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesParSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadGrid(int id){
         try {
            model.setRowCount(0);
            for(Machine machine : daoMachine.findMachineBySalle(id)){
                model.addRow(new Object[]{
                    machine.getId(),
                    machine.getRef(),
                    machine.getMarque(),
                    machine.getPrix()
                });
//                System.out.println(machine);
            }
                
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesForm.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combo_salles = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_machines = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Filtrer Machines Par Salle");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("List des Machine par Sale"));

        jLabel1.setText("Code :");

        combo_salles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_sallesItemStateChanged(evt);
            }
        });
        combo_salles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_sallesActionPerformed(evt);
            }
        });

        tbl_machines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Ref", "Marque", "Prix"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_machines);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(combo_salles, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo_salles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_sallesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_sallesItemStateChanged
        
        String selectedSalleCode = combo_salles.getSelectedItem().toString();
//        System.err.println(selectedSalleCode);
        int id = -1;
        try {
            for (Salle salle : daoSalle.findAll()) { 
                if (selectedSalleCode.equals(salle.getCode())) {
                    id = salle.getId();
//                    System.out.println(id);
                }
            }
            loadGrid(id);
        } catch (RemoteException ex) {
            Logger.getLogger(MachinesParSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combo_sallesItemStateChanged

    private void combo_sallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_sallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox combo_salles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_machines;
    // End of variables declaration//GEN-END:variables
}