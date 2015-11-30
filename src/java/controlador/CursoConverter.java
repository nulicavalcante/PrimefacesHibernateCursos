/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CursoDAO;
import dao.DAOFactory;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modelo.Curso;

/**
 *
 * @author teste
 */

@FacesConverter(forClass=Curso.class)

public class CursoConverter implements Converter {

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if(value != null && value.trim().length() > 0) {
            try {
                System.out.println("Value é " + value);
                 CursoDAO dao = DAOFactory.criarCursoDAO();
                 return  dao.getPorId(Integer.valueOf(value));
                 
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid curso."));
            }
        }
        else {
            return null;
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
     if(object != null) {
            return String.valueOf(((Curso) object).getId());
        }
        else {
            return null;
        }
    }
}