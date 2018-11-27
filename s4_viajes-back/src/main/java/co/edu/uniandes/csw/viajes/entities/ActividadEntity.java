/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jecantorm
 */

@Entity
public class ActividadEntity extends ServicioEntity implements Serializable{
    
    @PodamExclude
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuiaEntity> guias = new ArrayList<GuiaEntity>();

    private List<Long> idsGuias=new ArrayList<>();

    public ActividadEntity(){
        super();

    }
    
    /**
     * Devuelve las guias de la actividad.
     *
     * @return Lista de entidades de Actividad.
     */
    public List<GuiaEntity> getGuias() {
        return guias;
    }
    
    /**
     * Modifica las guias de la actividad.
     *
     * @param guias Los nuevos libros.
     */
    public void setGuias(List<GuiaEntity> guias) {
        this.guias = guias;
    }
    
    public void addGuia(GuiaEntity guia)
    {
        if(guia!=null)
             guias.add(guia);
    }
     public void addGuiaFirst(GuiaEntity guia)
    {
        if(guia!=null)
             guias.add(0,guia);
    }

    public List<Long> getIdsGuias() {
        if(idsGuias==null)
            idsGuias=new ArrayList<>();
        return idsGuias;
    }

    public void setIdsGuias(List<Long> idsGuias) {
        this.idsGuias = idsGuias;
    }
    
   public void addIdGuia(Long idGuia) {

        idsGuias.add(0,idGuia);
    }
     public void deleteIdGuia(Long idGuia) {
        boolean ya=false;
        for(int i=0;i<idsGuias.size()&&!ya;i++)
            if(idsGuias.get(i)==idGuia)
            {
                idsGuias.remove(i);
                ya=true;
            }
    }

     

}
