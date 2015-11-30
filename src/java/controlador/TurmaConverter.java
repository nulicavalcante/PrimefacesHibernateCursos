/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DAOFactory;
import dao.TurmaDAO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modelo.Turma;

/**
 *
 * @author aluno
 */
@FacesConverter(forClass=Turma.class)
public class TurmaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if(value != null && value.trim().length() > 0) {
            try {
                System.out.println("Value é " + value);
                 TurmaDAO dao = DAOFactory.criarTurmaDAO();
                 return  dao.getPorId(Integer.valueOf(value));
                 
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Turma."));
            }
        }
        else {
            return null;
        }
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null){
        Turma turma = (Turma) o;
        return turma.getId().toString();
        }
        return null;
    }
    
}
