/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *Clase que representa un vuelo en la persistencia y permite su serialización
 * 
 * @author jf.torresp
 */
@Entity
public class VueloEntity extends TransporteEntity implements Serializable {
    
    public VueloEntity(){
       super();
   }
    
}
