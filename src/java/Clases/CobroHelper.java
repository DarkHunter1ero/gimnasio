/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DataBase.DBGenericClass;
import DataBase.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego
 */
public class CobroHelper extends DBGenericClass<Cobro>{

    
    private Session session;
    private Transaction trans;
    
    public CobroHelper() {
        super(Cobro.class);
        session=super.getSession();
    }
    
    
    public List<Cobro> findAll() {
        List<Cobro> list = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = session.createCriteria(Cobro.class);
            list = cri.list();
            for (Cobro cobro : list) {
                Hibernate.initialize(cobro.getCliente());
            }
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return list;
    }
    
    public List<Cobro> findAllInitCobAct() {
        List<Cobro> list = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = session.createCriteria(Cobro.class);
            list = cri.list();
            for (Cobro cobro : list) {
                Hibernate.initialize(cobro.getCliente());
                Hibernate.initialize(cobro.getCobroActividad());
            }
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return list;
    }
    
    public List<Cobro> findAllInitAll() {
        List<Cobro> list = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = session.createCriteria(Cobro.class);
            list = cri.list();
            for (Cobro cobro : list) {
                Hibernate.initialize(cobro.getCliente());
                Hibernate.initialize(cobro.getCobroActividad());
                List<CobroActividad> listActCob = cobro.getCobroActividad();
                for (CobroActividad actCob : listActCob) {
                    Hibernate.initialize(actCob.getActividad());
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return list;
    }
    
    public void createWithRelations(Cobro cobro){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.persist(cobro);
            for(CobroActividad ca:cobro.getCobroActividad()){
                ca.setIdCobro(cobro.getId());
                session.persist(ca);
            }
            trans.commit();
        }catch(Exception e){
            trans.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    public List<Cobro> findByClienteInitAll(String CICliente) {
        List<Cobro> list = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = session.createCriteria(Cobro.class);
            cri.createCriteria("cliente", "c");
            cri.add(Restrictions.eq("c.CI", CICliente));
            list = cri.list();
            for (Cobro cobro : list) {
                Hibernate.initialize(cobro.getCliente());
                Hibernate.initialize(cobro.getCobroActividad());
                List<CobroActividad> listActCob = cobro.getCobroActividad();
                for (CobroActividad actCob : listActCob) {
                    Hibernate.initialize(actCob.getActividad());
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return list;
    }
}
