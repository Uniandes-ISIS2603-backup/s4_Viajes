/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)

public class CarritoComprasPersistenceTest 
        
{
       @Inject 
    private CarritoComprasPersistence carritoComprasPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CarritoComprasEntity> data = new ArrayList<CarritoComprasEntity>();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
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
            em.joinTransaction();
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
        em.createQuery("delete from BookEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CarritoComprasEntity entity = factory.manufacturePojo(CarritoComprasEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Carrito.
     */
    @Test
    public void createCarritoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CarritoComprasEntity newEntity = factory.manufacturePojo(CarritoComprasEntity.class);
        CarritoComprasEntity result = carritoComprasPersistence.create(newEntity);

        Assert.assertNotNull(result);

        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
     
    }

    /**
     * Prueba para consultar la lista de Carritos.
     */
    @Test
    public void getCarritosTest() {
        List<CarritoComprasEntity> list = carritoComprasPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CarritoComprasEntity ent : list) {
            boolean found = false;
            for (CarritoComprasEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Carrito.
     */
    @Test
    public void getCarritoTest() {
        CarritoComprasEntity entity = data.get(0);
        CarritoComprasEntity newEntity = carritoComprasPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
     
    }

    /**
     * Prueba para eliminar un Carrito.
     */
    @Test
    public void deleteCarritoTest() {
        CarritoComprasEntity entity = data.get(0);
        carritoComprasPersistence.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book.
     */
    @Test
    public void updateBookTest() {
        CarritoComprasEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CarritoComprasEntity newEntity = factory.manufacturePojo(CarritoComprasEntity.class);

        newEntity.setId(entity.getId());

        carritoComprasPersistence.update(newEntity);

        CarritoComprasEntity resp = em.find(CarritoComprasEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
}
