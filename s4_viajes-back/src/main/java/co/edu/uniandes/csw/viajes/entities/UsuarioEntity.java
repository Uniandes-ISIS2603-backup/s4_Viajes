/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author n.segura
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    
    private int edad;
    private String documento;
    private String nombre;
    private String userName;
    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<PagoEntity> pagos = new ArrayList<PagoEntity>();
    private Boolean hasLoggedIn;
    @OneToOne(mappedBy="usuario", cascade = CascadeType.PERSIST)
    private CarritoComprasEntity carritoCompras;
    
    
      /**
     * Devuelve la edad del usuario.
     *
     * @return the edad
     */
    public int getEdad()
    {
        return edad;
    }
     /**
     * Devuelve el documento del usuario.
     *
     * @return the documento
     */
    public String getDocumento()
    {
        return documento;
    }
    
    
    public CarritoComprasEntity getCarrito()
    {
        return carritoCompras;
    }
    
    public CarritoComprasEntity setCarrito()
    {
        return carritoCompras;
    }
     /**
     * Devuelve el nombre del usuario.
     *
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }
     /**
     * Devuelve el user name del usuario.
     *
     * @return the user name
     */
    
    public String getUserName()
    {
        return userName;
    }
    
     /**
     * Devuelve la lista de pagos del usuario.
     *
     * @return the user name
     */
    
    public List<PagoEntity> getPagos()
    {
        return pagos;
    }
    
    
         /**
     * Devuelve la ocndición del usuario en la aplicación.
     *
     * @return the user state
     */
    
    
     public Boolean hasLoggedIn()
    {
        return hasLoggedIn;
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
       this.hasLoggedIn= pHasLoggedIn;
    } 
    
     public void setNombre(String pNombre)
    {
       this.nombre= pNombre;
    } 
    
}
