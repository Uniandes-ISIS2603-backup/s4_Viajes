/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AdministradorEntity;
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
public class AdministradorDetailDTO extends AdministradorDTO implements Serializable {
    
    
     //Relación con cero o muchos usuarios.
    private List<UsuarioDTO> usuarios;
    
    public AdministradorDetailDTO()
    {
        super();
    }
    
    /**
     * Crea un objeto AdministradorDetailDTO a partir de un objeto AdministradorEntity
     * incluyendo los atributos de Administrador DTO.
     *
     * @param administradorEntity Entidad UsuarioEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public AdministradorDetailDTO(AdministradorEntity administradorEntity) {
        super(administradorEntity);
        if (administradorEntity != null) {
            usuarios = new ArrayList<>();
            for(UsuarioEntity entityUsuario: administradorEntity.getUsuarios())
            {
                usuarios.add(new UsuarioDTO(entityUsuario));
            }    
        }  
    }
    
    
    /**
     * Convierte un objeto AdministradorDetailDTO a AdministradorEntity incluyendo los
     * atributos de AdministradorDTO.
     *
     * @return Nuevo objeto AdministradorEntity.
     *
     */
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = super.toEntity();
        if (usuarios != null) {
            List<UsuarioEntity> usuariosEntity = new ArrayList<>();
            for (UsuarioDTO dtoUsuario : usuarios) {
                usuariosEntity.add(dtoUsuario.toEntity());
            }
            administradorEntity.setUsuarios(usuariosEntity);
        }
     
        return administradorEntity;
    } 
    
    
    /**
     * Obtiene la lista de usuarios
     *
     * @return los usuarios
     */

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }
    
    /**
     * Establece la lista de usuarios
     * @param usuarios
     */

    public void setPagos(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    
    
       @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
}
