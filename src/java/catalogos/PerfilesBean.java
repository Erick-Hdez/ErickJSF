/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import controles.SAccesosJpaController;
import controles.SPerfilesAccesosJpaController;
import controles.SPerfilesJpaController;
import entidades.SAccesos;
import entidades.SPerfiles;
import entidades.SPerfilesAccesos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.model.DualListModel;
import utils.TraeDatoSesion;

/**
 *
 * @author Erick Corral 
 */
public class PerfilesBean {
 
    private SPerfiles perfiles;
    private List<SPerfiles> listaPerfiles;
    private List<SAccesos> listaAccesos;
    private List<SAccesos> listaAccesosAsignados;
    private DualListModel<SAccesos> dualListAccesos;
    
    public PerfilesBean() {
        mostrarListaPerfiles();
        mostrarListaAccesos();
        perfiles = new SPerfiles();
        dualListAccesos = new DualListModel<>(getListaAccesos(), getListaAccesosAsignados());
    }
    
    public void mostrarListaPerfiles() {
        SPerfilesJpaController modelo = new SPerfilesJpaController();
        setListaPerfiles(modelo.findSPerfilesEntities());
    }
    
    public void mostrarListaAccesos() {
        SAccesosJpaController modelo = new SAccesosJpaController();
        setListaAccesos(modelo.findSAccesosEntities());
    }
    
    public void registrarPerfil() {
        List<SAccesos> ListaAccesosStr = new ArrayList<>();
        SPerfilesAccesos perfilesAccesos = new SPerfilesAccesos();
        
        SPerfilesJpaController modeloPerfiles = new SPerfilesJpaController();
        SPerfilesAccesosJpaController modeloPerfilesAccesos = new SPerfilesAccesosJpaController();
        perfiles.setActivo(true);
        perfiles.setFechaAlta(new Date());
        perfiles.setFechaServidor(new Date());
        perfiles.setIdUsuarioModifica(TraeDatoSesion.traerIdUsuario());
        
        modeloPerfiles.create(perfiles);
        
                
    }

        //<editor-fold defaultstate="collapsed" desc="GETS y SETS">
        /**
         * @return the perfiles
         */
        public SPerfiles getPerfiles() {
            return perfiles;
        }
        
        /**
         * @param perfiles the perfiles to set
         */
        public void setPerfiles(SPerfiles perfiles) {
            this.perfiles = perfiles;
        }
        
        /**
         * @return the listaPerfiles
         */
        public List<SPerfiles> getListaPerfiles() {
            return listaPerfiles;
        }
        
        /**
         * @param listaPerfiles the listaPerfiles to set
         */
        public void setListaPerfiles(List<SPerfiles> listaPerfiles) {
            this.listaPerfiles = listaPerfiles;
        }
        
        /**
         * @return the listaAccesos
         */
        public List<SAccesos> getListaAccesos() {
            return listaAccesos;
        }
        
        /**
         * @param listaAccesos the listaAccesos to set
         */
        public void setListaAccesos(List<SAccesos> listaAccesos) {
            this.listaAccesos = listaAccesos;
        }
        
        /**
         * @return the listaAccesosAsignados
         */
        public List<SAccesos> getListaAccesosAsignados() {
            return listaAccesosAsignados;
        }
        
        /**
         * @param listaAccesosAsignados the listaAccesosAsignados to set
         */
        public void setListaAccesosAsignados(List<SAccesos> listaAccesosAsignados) {
            this.listaAccesosAsignados = listaAccesosAsignados;
        }
        
        /**
         * @return the dualListAccesos
         */
        public DualListModel<SAccesos> getDualListAccesos() {
            return dualListAccesos;
        }
        
        /**
         * @param dualListAccesos the dualListAccesos to set
         */
        public void setDualListAccesos(DualListModel<SAccesos> dualListAccesos) {
            this.dualListAccesos = dualListAccesos;
        }
//</editor-fold>
}
