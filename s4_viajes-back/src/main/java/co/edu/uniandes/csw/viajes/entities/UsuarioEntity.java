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
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


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
    private String password;
    
    private List<Long> idsCombos=new ArrayList<>();
    private List<Long> idsPagos=new ArrayList<>();
    private List<Long> idsEntradas=new ArrayList<>();
    private List<Long> idsMedallas=new ArrayList<>();


    @PodamExclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PagoEntity> pagos = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComboEntity> combos= new ArrayList<>();
    
  
    @PodamExclude
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntradaEntity> entradas = new ArrayList<EntradaEntity>();
    
    @PodamExclude
    @OneToMany
    private List<MedallaEntity> medallas = new ArrayList<MedallaEntity>();
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
     * Devuelve la lista de entradas del usuario.
     *
     * @return lista de entradas
     */
    
    public List<EntradaEntity> getEntradas()
    {
        return entradas;
    }
    
          /**
     * Devuelve la lista de medalla del usuario.
     *
     * @return lista de medallas
     */
    
    public List<MedallaEntity> getMedallas()
    {
        return medallas;
    }
    
      /**
     * Actualiza la lista de entradas del usuario con la que entra por parametro.
     *
     * @param pEntradas nueva lista de entradas
     */
    
    public void setEntradas(List<EntradaEntity> pEntradas)
    {
        this.entradas = pEntradas;
    }
    
    
    public void addEntrada(EntradaEntity entrada)
    {
        if(entrada!=null)
             entradas.add(entrada);
    }
     public void addEntradaFirst(EntradaEntity entrada)
    {
        if(entrada!=null)
             entradas.add(0,entrada);
    }
    
              /**
     * Actualiza la lista de medallas del usuario con la que entra por parametro.
     *
     * @param pMedallas nueva lista de medallas
     */
    
    public void setMedallas(List<MedallaEntity> pMedallas)
    {
        this.medallas = pMedallas;
    }
    
    public void addMedalla(MedallaEntity medalla)
    {
        if(medalla!=null)
             medallas.add(medalla);
    }
     public void addMedallaFirst(MedallaEntity medalla)
    {
        if(medalla!=null)
             medallas.add(0,medalla);
    }
    
         /**
     *  Cambia la lista de pagos
     * @param pagos la lista de pagos con la que se actualiza la informacion.
     */
    public void setPagos(List<PagoEntity> pagos)
            
    {
        this.pagos = pagos;
        
    }
    
    
    public void addPago(PagoEntity pago)
    {
        if(pago!=null)
             pagos.add(pago);
    }
     public void addPagoFirst(PagoEntity pago)
    {
        if(pago!=null)
             pagos.add(0,pago);
    }
    
     
     /**
     * Modifica la edad del usuario.
     *
     * @param pEdad
     */
     public void setEdad(int pEdad)
    {
        this.edad = pEdad;
    }
    
     /**
     * Modifica el documento del usuario.
     *
     * @param pDocumento
     */
    public void setDocumento(String pDocumento)
    {
       this.documento = pDocumento;
    }
    
     /**
     * Modifica el UserName del usuario.
     *
     * @param pUserName 
     */
    public void setUserName(String pUserName)
    {
       this.userName= pUserName;
    }
    
    
     /**
     * Modifica el nombre del usuario.
     *
     * @param pNombre 
     */  
     public void setNombre(String pNombre)
    {
       this.nombre= pNombre;
    } 

    public List<Long> getIdsCombos() {
        return idsCombos;
    }

    public void setIdsCombos(List<Long> idsCombos) {
        this.idsCombos = idsCombos;
    }
    
    public void addIdCombo(Long idCombo) {
       idsCombos.add(0,idCombo);
    }
    public void deleteIdCombo(Long idCOmbo) {
        boolean ya=false;
        for(int i=0;i<idsCombos.size()&&!ya;i++)
            if(idsCombos.get(i)==idCOmbo)
            {
                idsCombos.remove(i);
                ya=true;
            }
    }
    

    public List<Long> getIdsPagos() {
        return idsPagos;
    }

    public void setIdsPagos(List<Long> idsPagos) {
        this.idsPagos = idsPagos;
    }
    
    public void addIdPago(Long idPago) {
         
        idsPagos.add(0,idPago);
    }
    
     public void deleteIdPago(Long idPago) {
        boolean ya=false;
        for(int i=0;i<idsPagos.size()&&!ya;i++)
            if(idsPagos.get(i)==idPago)
            {
                idsPagos.remove(i);
                ya=true;
            }
    }

    public List<ComboEntity> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboEntity> combos) {
        this.combos = combos;
    }
    
    public void addCombo(ComboEntity combo)
    {
        if(combo!=null)
             combos.add(combo);
    }
     public void addComboFirst(ComboEntity combo)
    {
        if(combo!=null)
             combos.add(0,combo);
    }

    public List<Long> getIdsEntradas() {
        return idsEntradas;
    }

    public void setIdsEntradas(List<Long> idsEntradas) {
        this.idsEntradas = idsEntradas;
    }

     public void addIdEntrada(Long idEntrada) {
         
        idsEntradas.add(0,idEntrada);
    }
    
     public void deleteIdEntrada(Long idEntrada) {
        boolean ya=false;
        for(int i=0;i<idsEntradas.size()&&!ya;i++)
            if(idsEntradas.get(i)==idEntrada)
            {
                idsEntradas.remove(i);
                ya=true;
            }
    }
     
    public List<Long> getIdsMedallas() {
        return idsMedallas;
    }

    public void setIdsMedallas(List<Long> idsMedallas) {
        this.idsMedallas = idsMedallas;
    }
     
    public void addIdMedalla(Long idMedalla) {
         
        idsMedallas.add(0,idMedalla);
    }
    
     public void deleteIdMedalla(Long idMedalla) {
        boolean ya=false;
        for(int i=0;i<idsMedallas.size()&&!ya;i++)
            if(idsMedallas.get(i)==idMedalla)
            {
                idsMedallas.remove(i);
                ya=true;
            }
    }
     
     
    
}
