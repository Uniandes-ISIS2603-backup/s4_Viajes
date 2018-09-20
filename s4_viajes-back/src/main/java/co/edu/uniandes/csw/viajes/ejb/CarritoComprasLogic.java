/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.CarritoComprasPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;



/**
 *
 * @author n.segura
 */
@Stateless 
public class CarritoComprasLogic {
  
    
        private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private CarritoComprasPersistence persistence;

    /**
     * Se encarga de crear un CarritoDeCompras en la base de datos.
     *
     * @param carritoComprasEntity Objeto de carritoComprasEntity con los datos nuevos
     * @return Objeto de CarritoComprasEntity con los datos nuevos y su ID.
     */
    public CarritoComprasEntity createCarrito(CarritoComprasEntity carritoComprasEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del administrador");
        CarritoComprasEntity newCarritoComprasEntity = persistence.create(carritoComprasEntity);
        
             if (carritoComprasEntity.getCosto()!=0) {
           throw new BusinessLogicException("El costo inicial del carrito no puede ser diferente de cero.");
        }
      
    
         return newCarritoComprasEntity;
       
    }
    
    public boolean validateContrasena(Double pCosto)
    {
       
    return pCosto>0;
}
}
