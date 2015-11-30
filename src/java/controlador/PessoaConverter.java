/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DAOFactory;
import dao.PessoaDAO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modelo.Pessoa;

/**
 *
 * @author teste
 */
@FacesConverter(forClass=Pessoa.class)
public class PessoaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    
                 if(value != null && value.trim().length() > 0) {
            try {
                System.out.println("Value é " + value);
                 PessoaDAO dao = DAOFactory.criarPessoaDAO();
                 return  dao.getPorId(Integer.valueOf(value));
                 
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Pessoa."));
            }
        }
        else {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
     if(object != null) {
            return String.valueOf(((Pessoa) object).getId());
        }
        else {
            return null;
        }

    }
    
}
