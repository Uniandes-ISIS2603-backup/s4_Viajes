    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the e   ditor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * /**
 * UsuarioDTO Objeto de transferencia de datos de Usuarios. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": string, 
 *      "edad": integer,
 *      "user_name": string,
 *      "hasLoggedIn" : boolean,
 *      "documento" : string     
 *   }
 * </pre> Por ejemplo un usuario se representa asi:<br>
 *
 * <pre>
 *
 *  {
 *      "nombre" : "Nicolas",     
 *      "edad" : "19",      
 *      "user_name" : "Nikthekill", 
 *      "hasLoggedIn" :"true",
 *      "documento" : "991219104108"
 *  }
 *
 * </pre>
 *
 * @author Nicolás Segura Castro
 */
public class UsuarioDTO implements Serializable{
    // Atributos
    private String documento;
    private String nombre;
    private String userName;
    private Boolean hasLoggedIn;
    private String contraseña;
    private int edad;


   public UsuarioDTO(){
       
   }
   
   public UsuarioDTO(UsuarioEntity usuarioEntity)
   {
    
       if(usuarioEntity!=null)
       {
           this.documento = usuarioEntity.getDocumento();
           this.edad = usuarioEntity.getEdad();
           this.userName = usuarioEntity.getUserName();
           this.hasLoggedIn = usuarioEntity.hasLoggedIn();
           this.nombre = usuarioEntity.getNombre();
          
       }
   }
   
   //Métodos//
    /**
     * Obtiene el nombre de un usuario.
     *
     * @return nombre del usuario.
     */
   
   public String getNombre(){
       return nombre;
   }
   
       /**
     * Obtiene la edad de un usuario.
     *
     * @return edad del usuario.
     */
   public int getEdad()
   {
       return edad;
   }
       /**
     * Obtiene el documento de un usuario.
     *
     * @return documento del usuario.
     */
   public String getDocumento()
   {
       return documento;
   }
   
       /**
     * Obtiene el userName de un usuario.
     *
     * @return userName del usuario.
     */
   
   public String getUserName()
   {
      return userName;
   }

    public Boolean hasLoggedIn()
    {
        return hasLoggedIn;
    }
    
      
   public void setNombre(String pNombre){

       this.nombre = pNombre;
   }
   
   public void setEdad(int pEdad)
   {
       this.edad = pEdad;
   }
   
   public void setDocumento(String pDocumento)
   {
        this.documento = pDocumento;
   }
   
   public void setUserName(String pUserName)
   {
       this.userName= pUserName;
        
        }

    public void setHasLoggedIn(Boolean pHasLoggedIn)
    {
        this.hasLoggedIn = pHasLoggedIn;
    }
    
    
    

    public UsuarioEntity toEntity() {
      
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre(this.nombre);
        usuarioEntity.setUserName(this.userName);
        usuarioEntity.setHasLoggedIn(this.hasLoggedIn);
        usuarioEntity.setDocumento(this.documento);
        usuarioEntity.setEdad(this.edad);

       
        
        return usuarioEntity;
        
        
    }
      
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
     
    
}
