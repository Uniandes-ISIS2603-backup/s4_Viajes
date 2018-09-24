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
    
    
    
   
    
      
     /**
     * Obtener un carrito por medio de su id.
     *
     * @param carritoId: id del carrito para ser buscado.
     * @return el carrito solicitado por medio de su id.
     */
    public CarritoComprasEntity getCarrito(Long carritoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar usuario con id = {0}", carritoId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        CarritoComprasEntity carrito = persistence.find(carritoId);
        if (carrito == null) {
            LOGGER.log(Level.SEVERE, "El carrito con el id = {0} no existe", carritoId);
           throw new BusinessLogicException("El carrito consultado no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el carrito con id = {0}", carritoId);
        return carrito;
    }
    
     /**
     * Valida el costo de un c
     * @param pCosto costo del carrito de compras.
     * @return La validacion del costo. 
     */
   
    public boolean validateCosto(Double pCosto)
    {  
    return pCosto>0;
    }
    
    
    /**
     * Actualiza un carrito de compras por medio de su id.
     *
     * @param carritoComprasId: id del carrito de compras para ser actualizado.
     * @return el carritoDeCompras solicitado por medio de su id.
     */
    public CarritoComprasEntity updateCarritoCompras(Long carritoComprasId, CarritoComprasEntity carritoComprasEntity) throws BusinessLogicException {
   
        if (carritoComprasEntity.getNombre()==null) {
           throw new BusinessLogicException("No es posible actualizar un carrito con nombre nulo");
        }
        
         if (carritoComprasEntity.getCosto()<0) {
           throw new BusinessLogicException("No es posible actualizar un carrito con valor negativo");
        }
                   CarritoComprasEntity carrito = persistence.update(carritoComprasEntity);

        return carrito;
    }
    
    
    /**
     * Borrar un carrito de compras
     *
     * @param carritoID: id del carrito de compras a borrar
     * @throws BusinessLogicException si el carrito de compras que se desea eliminar no existe.
     */
    public void deleteCarritoCompras(Long carritoID) throws BusinessLogicException {
                LOGGER.log(Level.INFO, "Empieza proceso de borrar carrito con id = {0}", carritoID);

         CarritoComprasEntity carritoEntity = persistence.find(carritoID);
        if (carritoEntity==null) {
            throw new BusinessLogicException("No es posible borrar un carrito de compras que no existe");
        }
          persistence.delete(carritoID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar administrador con id = {0}", carritoID);
       
     
    }
    
}
