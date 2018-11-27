/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * ProveedorDTO Objeto de transferencia de datos de Proveedores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "user": String,
 *      "password": String,
 *      "puntaje": Double
 *   }
 * </pre> Por ejemplo un proveedor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "nombre": "Avianca",
 *      "user": "avianca123",
 *      "password": "Avianca2018",
 *      "puntaje": 4.2
 *   }
 *
 * </pre>
 * 
 * @author Juan Felipe Torres
 */
public class ProveedorDTO implements Serializable {

    //Atributos//
    private Long proveedorId;
    
    private String username;
    
    private String password;
    
    private String nombre;
    
    private int puntuacion;
    
    private String imagen;

    //Constructores//
    
    /**
     * Constructor por defecto
     */
    public ProveedorDTO(){
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param proveedorEntity: Es la entidad que se va a convertir a DTO
     */
    public ProveedorDTO(ProveedorEntity proveedorEntity) {
        if (proveedorEntity != null) {
            this.proveedorId = proveedorEntity.getId();
            this.nombre = proveedorEntity.getNombre();
            this.username = proveedorEntity.getUser();
            this.password = proveedorEntity.getPassword();
            this.puntuacion = proveedorEntity.getPuntuacion();
            this.imagen=proveedorEntity.getImagen();
        }
    }


    //Métodos//

    /**
     * Obtiene el id de un proveedor.
     *
     * @return id del proveedor.
     */
    public Long getId() {
        return proveedorId;
    }
    
   
    /**
     * Obtiene la contraseña de un proveedor.
     *
     * @return password del proveedor.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el nombre de un proveedor.
     *
     * @return nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica (set) el id de un proveedor por el ingresado por parámetro.
     *
     * @param pId nuevo Id que modificará el actual.
     */
    public void setId(Long pId) {
        proveedorId = pId;
    }
   

    /**
     * Modifica (set) la contraseña de un proveedor por la ingresada por
     * parámetro.
     *
     * @param pPassword nueva contraseña que modificará la actual.
     */
    public void setPassword(String pPassword) {
        password = pPassword;
    }

    /**
     * Modifica (set) el nombre de un proveedor por el ingresado por parámetro.
     *
     * @param pNombre nuevo nombre que modificará el actual.
     */
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

  

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ProveedorEntity toEntity() {
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setNombre(this.nombre);
        proveedorEntity.setUsername(this.username);
        proveedorEntity.setPassword(this.password);
        proveedorEntity.setPuntuacion(this.puntuacion);
        proveedorEntity.setImagen(this.imagen);
        return proveedorEntity;
    }

    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }   
}
