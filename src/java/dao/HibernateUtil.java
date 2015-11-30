package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Anderson Pazin
 */
public class HibernateUtil {
    private static final SessionFactory sessao = 
                                buildSessionFactory();
    
    private static SessionFactory buildSessionFactory(){
        try{
            AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.configure("hibernate.cfg.xml");
            return cfg.buildSessionFactory();
        } catch (Exception ex){
            System.out.println("Criação Hibernate falhou!");
            ex.printStackTrace();
            return null;
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessao;
    }
}
