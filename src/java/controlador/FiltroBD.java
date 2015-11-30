/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.HibernateUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.SessionFactory;

/**
 *
 * @author Anderson Pazin
 */
@WebFilter(filterName = "FiltroBD", urlPatterns = {"/*"})
public class FiltroBD implements Filter {
    
    private static final boolean debug = true;
    private FilterConfig filterConfig = null;
    private SessionFactory sf;
    
    public FiltroBD() {
    }    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            this.sf.getCurrentSession().beginTransaction();
            //System.out.println("FUI!!!!");
            chain.doFilter(request, response);
            //System.out.println("VOLTEI!!!!");
            this.sf.getCurrentSession().getTransaction().commit();
            this.sf.getCurrentSession().close();
        }catch (Exception ex){
            ex.printStackTrace();
//            if(this.sf.getCurrentSession().getTransaction().isActive()){
//                this.sf.getCurrentSession().getTransaction().rollback();
//            }
        }
    }

    @Override
    public void destroy() {

    }
}
