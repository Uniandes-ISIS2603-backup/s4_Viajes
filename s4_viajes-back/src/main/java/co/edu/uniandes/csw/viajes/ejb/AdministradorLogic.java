/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.AdministradorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.AdministradorPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author estudiante
 */
@Stateless
public class AdministradorLogic {
    
    
        private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private AdministradorPersistence persistence;

    /**
     * Se encarga de crear un Administrador  en la base de datos.
     *
     * @param administradorEntity Objeto de administradorEntity con los datos nuevos
     * @return Objeto de AdministradorEntity con los datos nuevos y su ID.
     */
    public AdministradorEntity createAdministrador(AdministradorEntity administradorEntity, String pContraseña) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del administrador");
        AdministradorEntity newAdministradorEntity = persistence.create(administradorEntity);
        
        if(pContraseña!="Tr1pBvld3rUltr4S3cr3tP@ssw0rd")
        {
            throw new BusinessLogicException("CONTRASEÑA INCORRECTA!");
        }
        
         if(persistence.findByUserName(administradorEntity.getUserName())!=null)
        {
            throw new BusinessLogicException("El nombre de usuario ya ha sido tomado");
        }
    
         return newAdministradorEntity;
       
    }
    
    public boolean validateContrasena(String pContrasena)
    {
        Pattern pat = Pattern.compile("[.]{8,})");
        Matcher mat = pat.matcher(pContrasena);
        return (mat.matches() && !pContrasena.isEmpty());

    }

    public boolean validateNombre(String pNombre)
    {
        Pattern pat = Pattern.compile("[a-zA-Z]{1,}[.]{*})");
        Matcher mat = pat.matcher(pNombre);
        return (mat.matches() && !pNombre.isEmpty());

    }    
    
    
    /**
     * Actualiza un administrador por medio de su id.
     *
     * @param administradorId: id del administrador para ser actualizado.
     * @return el usuario solicitado por medio de su id.
     */
    public AdministradorEntity updateAdministrador(AdministradorEntity administradorEntity) throws BusinessLogicException {
   
        if (administradorEntity.getNombre()==null|administradorEntity.getUserName()==null) {
           throw new BusinessLogicException("No es posible actualizar un administrador con información nula");
        }
                   AdministradorEntity administrador = persistence.update(administradorEntity);

        return administrador;
    }
    
    
    /**
     * Borrar un administrador
     *
     * @param administradorId: id del administrador a borrar
     * @throws BusinessLogicException si el administrador no ingresa la contraseña adecuada.
     */
    public void deleteAdministrador(Long adminID, String pContraseña) throws BusinessLogicException {
                LOGGER.log(Level.INFO, "Empieza proceso de borrar administrador con id = {0}", adminID);

        if(pContraseña!="Tr1pBvld3rUltr4S3cr3tP@ssw0rd")
        {
            throw new BusinessLogicException("CONTRASEÑA INCORRECTA!");
        }
        
          persistence.delete(adminID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar administrador con id = {0}", adminID);
       
     
    }
    
    
}
