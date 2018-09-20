/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.logic;

import co.edu.uniandes.csw.viajes.ejb.CarritoComprasLogic;
import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.CarritoComprasPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.segura
 */


@RunWith(Arquillian.class)
public class CarritoComprasLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CarritoComprasLogic carritoComprasLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    
    private List<CarritoComprasEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoComprasEntity.class.getPackage())
                .addPackage(CarritoComprasLogic.class.getPackage())
                .addPackage(CarritoComprasPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        
        em.createQuery("delete from CarritoComprasEntity").executeUpdate();  
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CarritoComprasEntity entity = factory.manufacturePojo(CarritoComprasEntity.class);
            em.persist(entity);
            data.add(entity);
        }
       
    }

    /**
     * Prueba para crear un Carrito de Compras.
     */
    @Test
    public void createCarritoComprasTest() throws BusinessLogicException {
     
        CarritoComprasEntity newEntity = factory.manufacturePojo(CarritoComprasEntity.class);
        CarritoComprasEntity result = carritoComprasLogic.createCarrito(newEntity);
        Assert.assertNotNull(result);
        CarritoComprasEntity entity = em.find(CarritoComprasEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
    }

    /**
     * Prueba para consultar un carrito de compras. 
     */
    @Test
    public void getCarritoComprasTest() throws BusinessLogicException {
        CarritoComprasEntity entity = data.get(0);
        CarritoComprasEntity resultEntity =  carritoComprasLogic.getCarrito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());

    }
    
    
     /**
     * Prueba para eliminar un Carrito de Compras
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteCarritoComprasTest() throws BusinessLogicException {
        CarritoComprasEntity entity = data.get(0);
        carritoComprasLogic.deleteCarritoCompras(entity.getId());
        CarritoComprasEntity deleted = em.find(CarritoComprasEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
  /**
     * Prueba para actualizar un Carrito de compras.
     */
    @Test
    public void updateAuthorTest() throws BusinessLogicException{
        CarritoComprasEntity entity = data.get(0);
        CarritoComprasEntity pojoEntity = factory.manufacturePojo(CarritoComprasEntity.class);

        pojoEntity.setId(entity.getId());

        carritoComprasLogic.updateCarritoCompras(pojoEntity.getId(), pojoEntity);

        CarritoComprasEntity resp = em.find(CarritoComprasEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
     
    }
}
