/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package DataBase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

public abstract class DBGenericClass<T>{
    
    private Session session;
    private Transaction trans;
    private Class<T> entityClass;
    
    public DBGenericClass(Class<T> entityClass) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.getCurrentSession();
        this.entityClass = entityClass;
    }
    
    public Session getSession(){
        return this.session;
    }
    
    public void create(T entity){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.save(entity);
            trans.commit();
        }catch(Exception e){
            trans.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    public void update(T entity){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.update(entity);
            trans.commit();
        }catch(Exception e){
            trans.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    public void delete(T entity){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.delete(entity);
            trans.commit();
        }catch(Exception e){
            trans.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
//    public T findById(int id){
//        T object;
//        try{
//            session = HibernateUtil.getSessionFactory().openSession();
//            object = (T)session.get(entityClass, id);
//        }catch(Exception e){
//            throw e;
//        }finally{
//            session.close();
//        }
//        return object;
//    }
    
    public T findById(Object id){
        Type result;
        T object = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            ClassMetadata cmd = session.getSessionFactory().getClassMetadata(entityClass);
            result = cmd.getIdentifierType();
            if(result instanceof StringType ){
                String newId = String.valueOf(id);
                object = (T)session.get(entityClass, newId);
            }
            if(result instanceof IntegerType ){
                int newId = Integer.valueOf(String.valueOf(id));
                object = (T)session.get(entityClass, newId);
            }
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return object;
    }
    
//    public T findById(String id){
//        T object;
//        try{
//            session = HibernateUtil.getSessionFactory().openSession();
//            object = (T)session.get(entityClass, id);
//        }catch(Exception e){
//            throw e;
//        }finally{
//            session.close();
//        }
//        return object;
//    }
    
    public List<T> findAll() {
        List<T> list = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = session.createCriteria(entityClass);
            list = cri.list();
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return list;
    }
    
    public boolean existenRegistros() {
        List<T> list = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = session.createCriteria(entityClass);
            list = cri.list();
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        if((list!=null) && (!list.isEmpty())){
            return true;
        }else{
            return false;
        }
    }
    
    public int count() {
        Long result;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            result = (long)session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return result.intValue();
    }
    
    public Object getIdentifier(Object entity){
        Object result;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            ClassMetadata cmd = session.getSessionFactory().getClassMetadata(entityClass);
            result = cmd.getIdentifier(entity);
        }catch(Exception e){
            throw e;
        }finally{
            session.close();
        }
        return result;
    }
    
//    public boolean exist(Object id){
//        Type result;
//        T object = null;
//        try{
//            session = HibernateUtil.getSessionFactory().openSession();
//            ClassMetadata cmd = session.getSessionFactory().getClassMetadata(entityClass);
//            result = cmd.getIdentifierType();
//            if(result instanceof StringType ){
//                String newId = String.valueOf(id);
//                object = (T)session.get(entityClass, newId);
//            }
//            if(result instanceof IntegerType ){
//                int newId = Integer.valueOf(String.valueOf(id));
//                object = (T)session.get(entityClass, newId);
//            }
//        }catch(Exception e){
//            throw e;
//        }finally{
//            session.close();
//        }
//        return object;
//    }
    
    public boolean exist(T entity) {
        Object obj = getIdentifier(entity);
        return !(findById(obj) == null);
    }
}
