/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.MachineService;
import service.SalleService;
import util.HibernateUtil;

/**
 *
 * @author Guezir
 */
public class Test {
    
    public static void main(String[] args) {
        try {
            IDao<Machine> daoMachine = new MachineService();
            
            IDao<Salle> daoSalle = new SalleService();
            
            daoMachine.create(new Machine("ER44", "HP", 3000));
            daoMachine.create(new Machine("ER46", "MENOVO", 1100));
            daoMachine.create(new Machine("ER48", "HP", 5400));
            
            
            daoSalle.create(new Salle("cc2"));
            daoSalle.create(new Salle("LR"));
            daoSalle.create(new Salle("CC33"));
          
            
            for(Machine  m : daoMachine.findAll())
                System.out.println(m);
            
            
            for(Salle s : daoSalle.findAll())
                System.out.println(s);
            
            
             for(Machine s:daoMachine.findMachineBySalle(1))
                System.out.println(s);
        } catch (RemoteException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
