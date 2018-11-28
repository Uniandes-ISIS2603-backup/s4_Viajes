    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the e   ditor.
 */
package co.edu.uniandes.csw.viajes.dtos;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.io.Serializable;
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
 *      "documento" : "991219104108"
 *  }
 *
 * </pre>
 *
 * @author Nicolás Segura Castro
 */
public class UsuarioDTO implements Serializable{
    // Atributos
    
    private Long id;
    private String documento;
    private String nombre;
    private String userName;
    private String password;
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
           this.nombre = usuarioEntity.getNombre();
           this.id = usuarioEntity.getId();
           this.password = usuarioEntity.getPassword();
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
      
    public String getPassword() {
        return password;
    }
    

    public void setPassword(String password) {
        this.password = password;
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
     * Obtiene la edad de un usuario.
     *
     * @return edad del usuario.
     */
   public Long getId()
   {
       return id;
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

  
   
    /**
     * Modifica el nombre de un usuario.
     *
     * @param pNombre Nombre del usuario
     */
   public void setNombre(String pNombre){

       this.nombre = pNombre;
   }
   
   
   
      /**
     * Modifica la edad de un usuario.
     *
     * @param pEdad
     */   
   public void setEdad(int pEdad)
   {
       this.edad = pEdad;
   }
   
     /**
     * Modifica el id de un usuario.
     *
     * @param pId
     */
     public void setId(Long pId)
   {
       this.id = pId;
   }
   
    /**
     * Modifica el documento de un usuario.
     *
     * @param pDocumento
     */
   public void setDocumento(String pDocumento)
   {
        this.documento = pDocumento;
   }
   
   
     /**
     * Modifica el userName de un usuario.
     *
     * @param pUserName
     */
   public void setUserName(String pUserName)
   {
       this.userName= pUserName;
        
    }
   

  /**
     * Convierte el DTO en una Entity.
     * @return La entity creada a partir del DTO
     */
    public UsuarioEntity toEntity() {
      
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre(this.nombre);
        usuarioEntity.setUserName(this.userName);
        usuarioEntity.setDocumento(this.documento);
        usuarioEntity.setEdad(this.edad);
        usuarioEntity.setPassword(this.password);
        return usuarioEntity;
        
        
    }
      
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
     
    
}


