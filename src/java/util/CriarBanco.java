/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author 
 */
public class CriarBanco {
    public static void criarBanco(){
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure("hibernate.cfg.xml");
        SchemaExport se = new SchemaExport(cfg);
        se.create(true,true);
    }
    
    public static void main(String args[]){
        CriarBanco.criarBanco();
    }
}
