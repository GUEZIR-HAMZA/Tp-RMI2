/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Machine;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Guezir
 */
public class MachineService extends UnicastRemoteObject implements IDao<Machine> {

    public MachineService() throws RemoteException {
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
    public boolean create(Machine o) throws RemoteException {
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
    public boolean update(Machine o) throws RemoteException {
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
    public boolean delete(Machine o) throws RemoteException {
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
    public Machine findById(int id) throws RemoteException {
        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            Object result = session.get(Machine.class, id);
            if (result instanceof Machine) {
                return (Machine) result;
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
    public List<Machine> findAll() throws RemoteException {
        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            List<Machine> machines = session.createQuery("from Machine").list();
            return machines;
        } catch (HibernateException ex) {
            // Handle the exception as needed, e.g., log or throw a RemoteException.
            return null; // Return null in case of an error.
        }
    }

    @Override
    public List<Machine> findMachineBySalle(int id) {
        try (SessionWrapper sessionWrapper = new SessionWrapper()) {
            Session session = sessionWrapper.getSession();
            Query query = session.createQuery("from Machine WHERE salle.id = :id");
            query.setParameter("id", id);
            List<Machine> machines = query.list();
            return machines;
        } catch (HibernateException ex) {
            // Handle the exception as needed, e.g., log or throw an exception.
            return null; // Return null in case of an error.
        }
    }
}
