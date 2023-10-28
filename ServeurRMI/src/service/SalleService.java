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

    public class SessionWrapper implements AutoCloseable {
        private final Session session;

        public SessionWrapper() {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }

        public Session getSession() {
            return session;
        }

        @Override
        public void close() {
            if (session != null) {
                session.close();
            }
        }
    }
    @Override 
    public boolean create(Salle o) throws RemoteException {
        boolean etat;

        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            Transaction tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            etat = false;
        }

        return etat;
    }

    @Override
    public boolean update(Salle o) throws RemoteException {
        boolean etat;

        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            Transaction tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            etat = false;
        }

        return etat;
    }

    @Override
    public boolean delete(Salle o) throws RemoteException {

        boolean etat;

        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            etat = false;
        }

        return etat;
    }

    @Override
    public List<Salle> findAll() throws RemoteException {
        List<Salle> salles;
        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            salles = session.createQuery("from Salle ").list();
            return salles;
        } catch (HibernateException ex) {
            // Handle the exception as needed, e.g., log or throw a RemoteException.
            return null; // Return null in case of an error.
        }
    }

    @Override
    public Salle findById(int id) throws RemoteException {
        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            Object result = session.get(Salle.class, id);
            if (result instanceof Salle) {
                return (Salle) result;
            } else {
                // Handle the case where the result is not a Machine object, e.g., log or throw an exception.
                return null; // Return null in case of an error.
            }
        } catch (HibernateException ex) {
            // Handle the exception as needed, e.g., log or throw a RemoteException.
            return null; // Return null in case of an error.
        }
    }

    @Override
    public List<Machine> findMachineBySalle(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
