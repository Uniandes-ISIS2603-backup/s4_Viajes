/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;


import co.edu.uniandes.csw.viajes.entities.AdministradorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class AdministradorLogic {
    
    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());
    
  
    

public AdministradorEntity createUsuario(AdministradorEntity administradorEntity)    throws BusinessLogicException{
    
    LOGGER.log(Level.INFO, "Inicia el proceso de creación del usuario");
    LOGGER.log(Level.INFO, "Inicia el proceso de creación del usuario");
    
    return administradorEntity;
    
}
    
    
}
