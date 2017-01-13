/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DataBase.DBGenericClass;
import DataBase.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego
 */
public class ClienteHelper extends DBGenericClass<Cliente>{

    private Session session;
    
    public ClienteHelper() {
        super(Cliente.class);
        session=super.getSession();
    }
    
    public Cliente findByCI(String CI){
        Cliente cliente = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Cliente.class).add(Restrictions.eq("CI", CI));
            Object result = criteria.uniqueResult();
            if (result != null) {
                cliente = (Cliente) result;
            }
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return cliente;
    }
    
}
