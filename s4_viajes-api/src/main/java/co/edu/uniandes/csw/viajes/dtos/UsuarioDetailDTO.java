/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {
 
    //Relación con cero o muchos pagos
    private List<PagoDTO> pagos= new ArrayList<>();
    
    //Relación con cero o muchas medallas
    private List<MedallaDTO> medallas= new ArrayList<>();
    
    //Relación con cero o muchas entradas
    private List<EntradaDTO> entradas= new ArrayList<>();
    
    //Relación con cero o muchos combos
    private List<ComboDTO> combos= new ArrayList<>();
    
    private Long idPago;
    
    private Long idMedalla;

    private Long idEntrada;

    private Long idCombo;

    

    public UsuarioDetailDTO()
    {
        super();        
    }
    
     /**
     * Crea un objeto UsuarioDetailDTO a partir de un objeto UsuarioEntity
     * incluyendo los atributos de UsuarioDTO.
     *
     * @param usuarioEntity Entidad UsuarioEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public UsuarioDetailDTO(UsuarioEntity usuarioEntity) {
        super(usuarioEntity);
        if (usuarioEntity != null) {
            for(PagoEntity entityPagos: usuarioEntity.getPagos())
                 pagos.add(new PagoDTO(entityPagos));
            for(MedallaEntity entityMedallas: usuarioEntity.getMedallas())
                medallas.add(new MedallaDTO(entityMedallas));
            for(EntradaEntity entityEntradas: usuarioEntity.getEntradas())
                 entradas.add(new EntradaDTO(entityEntradas));
            for(ComboEntity entityCombos: usuarioEntity.getCombos())
               combos.add(new ComboDTO(entityCombos));           
        }  
        
    }
  
    /**
     * Convierte un objeto UsuarioDetailDTO a UsuarioEntity incluyendo los
     * atributos de UsuarioDTO.
     *
     * @return Nuevo objeto UsuarioEntity.
     *
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        if(idCombo!=null&&idCombo!=0l)
            usuarioEntity.addIdCombo(idCombo);
        if(idEntrada!=null&&idEntrada!=0l)
            usuarioEntity.addIdCombo(idEntrada);
        if(idMedalla!=null&&idMedalla!=0l)
            usuarioEntity.addIdCombo(idMedalla);
        if(idPago!=null&&idPago!=0l)
             usuarioEntity.addIdCombo(idPago);
        return usuarioEntity;
    }
    
    /**
     * Obtiene la lista de pagos del usuario
     *
     * @return los pagos
     */
    public List<PagoDTO> getPagos() {
        return pagos;
    }
    
    /**
     * Establece la lista de pagos del usuario
     * @param pagos
     */

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
     /**
     * Obtiene la lista de medallas del usuario
     *
     * @return las medallas
     */

    public List<MedallaDTO> getMedallas() {
        return medallas;
    }
    
    /**
     * Establece la lista de medallas del usuario
     * @param medallas
     */

    public void setMedallas(List<MedallaDTO> medallas) {
        this.medallas = medallas;
    }
    
     /**
     * Obtiene la lista de entradas del usuario
     *
     * @return las entradas
     */

    public List<EntradaDTO> getEntradas() {
        return entradas;
    }
    
    /**
     * Establece la lista de entradasas del usuario
     * @param entradas
     */

    public void setEntradas(List<EntradaDTO> entradas) {
        this.entradas = entradas;
    }

    public List<ComboDTO> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboDTO> combos) {
        this.combos = combos;
    }

    
    
       @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    }
