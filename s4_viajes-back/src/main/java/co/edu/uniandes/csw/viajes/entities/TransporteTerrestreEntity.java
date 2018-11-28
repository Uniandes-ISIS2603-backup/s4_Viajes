/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;


/**
 *
 * @author Ymespana
 */
@Entity
public class TransporteTerrestreEntity extends TransporteEntity implements Serializable {

   public TransporteTerrestreEntity(){
       super();
   }
  
}
