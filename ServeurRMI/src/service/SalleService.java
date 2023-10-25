/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Guezir
 */
public class SalleService extends UnicastRemoteObject implements IDao<Salle>{
    
    
    public SalleService() throws RemoteException {
        super();
    }

    @Override 
    public boolean create(Salle o) throws RemoteException {
         Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
        }finally {
            if(session != null)
                session.close();
        }
        return false;
    }

    @Override
    public boolean update(Salle o) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
        }finally {
            if(session != null)
                session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Salle o) throws RemoteException {
    
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
        }finally {
            if(session != null)
                session.close();
        }
        return false;
    }

    @Override
    public List<Salle> findAll() throws RemoteException {
   
    
        Session session = null;
        Transaction tx = null;
        List<Salle> sales = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            sales  = session.getNamedQuery("findAllNativeSale").list();
            tx.commit();
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
        }finally {
            if(session != null)
                session.close();
        }
        return sales;
    }

    @Override
    public Salle findById(int id) throws RemoteException {
    
         Session session = null;
        Transaction tx = null;
        Salle sale = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            sale  = (Salle) session.get(Salle.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
        }finally {
            if(session != null)
                session.close();
        }
        return sale;
    }

    @Override
    public List<Machine> findMachineBySalle(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
