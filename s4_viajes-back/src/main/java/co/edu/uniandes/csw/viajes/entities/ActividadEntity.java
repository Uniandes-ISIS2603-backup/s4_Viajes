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
    @OneToMany(mappedBy = "actividad")
    private List<GuiaEntity> guias = new ArrayList<GuiaEntity>();

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
     * @param books Los nuevos libros.
     */
    public void setGuias(List<GuiaEntity> books) {
        this.guias = books;
    }
    
    public void agregarGuia(GuiaEntity guia)
    {
      guias.add(guia);
    }

     

}
