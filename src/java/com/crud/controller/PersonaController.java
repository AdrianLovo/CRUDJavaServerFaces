package com.crud.controller;

import com.crud.entidades.Persona;
import com.crud.dao.PersonaFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.primefaces.context.RequestContext;


@ManagedBean(name = "personaController")
@SessionScoped
public class PersonaController implements Serializable {

    private Persona personaNuevo;
    private Persona personaUpdate;
    private List<Persona> listaPersona;
    
    @EJB
    private com.crud.dao.PersonaFacade ejbFacade;
    
    @PostConstruct
    public void init(){
        reiniciar();
    }
    
    public void reiniciar(){
        personaNuevo = new Persona();
        personaUpdate = new Persona();
        listaPersona = new ArrayList();
    }
    
    
    public PersonaController() {
    }
    
    //Listar Registros
    public List<Persona> personaTodos() {
        try {
            listaPersona = ejbFacade.findAll();
        } catch (NoResultException e) {
            System.out.println(e);
        }
        return listaPersona;
    }

    //Guardar Registro
    public void personaGuardar() {
        try {
            String genero = personaNuevo.getGenero().toUpperCase();
            personaNuevo.setGenero(genero);
            ejbFacade.create(personaNuevo);
            personaTodos();
            FacesMessage msg = new FacesMessage("Registro Agregado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formAllPersona:tblPersona");
            context.execute("PF('dlgGuardarPersona').hide();");
            reiniciar();
        } catch (PersistenceException e) {
            System.out.println(e);
        }
    }

    //Eliminar Registro
    public void personaEliminar(Persona registro) {
        try {
            ejbFacade.remove(registro);
            personaTodos();
            FacesMessage msg = new FacesMessage("Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formAllPersona:growl");
        } catch (EntityNotFoundException e) {
            System.out.println(e);
        }
    }

    //Actualizar Registro
    public void personaRegistro(Persona registro) {
        personaUpdate = registro;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formModificar:panelModificar");
        context.execute("PF('dlgModificarPersona').show();");

    }

    public void personaModificar() {
        try {
            ejbFacade.edit(personaUpdate);
            personaTodos();
            FacesMessage msg = new FacesMessage("Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formAllPersona:growl");
            context.execute("PF('dlgModificarPersona').hide();");
            reiniciar();
        } catch (EntityNotFoundException e) {
            System.out.println(e);
        }
    }

    public Persona getPersonaNuevo() {
        return personaNuevo;
    }

    public void setPersonaNuevo(Persona personaNuevo) {
        this.personaNuevo = personaNuevo;
    }

    public Persona getPersonaUpdate() {
        return personaUpdate;
    }

    public void setPersonaUpdate(Persona personaUpdate) {
        this.personaUpdate = personaUpdate;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }
    
    

    
}
