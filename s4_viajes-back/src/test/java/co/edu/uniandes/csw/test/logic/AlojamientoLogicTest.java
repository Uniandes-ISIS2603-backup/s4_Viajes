/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.test.logic;

import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
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
 * @author Ymespana
 */
@RunWith(Arquillian.class)
public class AlojamientoLogicTest {

//    private PodamFactory factory = new PodamFactoryImpl();
//     
//    @Inject
//    private AlojamientoLogic alojamientoLogic;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    private List<AlojamientoEntity> data = new ArrayList<>();
//    
//    private List<ProveedorEntity> proveedorData = new ArrayList<>();
//
//    /**
//     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
//     * El jar contiene las clases, el descriptor de la base de datos y el
//     * archivo beans.xml para resolver la inyección de dependencias.
//     */
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(AlojamientoEntity.class.getPackage())
//                .addPackage(AlojamientoLogic.class.getPackage())
//                .addPackage(AlojamientoPersistence.class.getPackage()) 
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * Configuración inicial de la prueba.
//     */
//    @Before
//    public void configTest() {
//        try {
//            utx.begin();
//            em.joinTransaction();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     */
//    private void clearData() {
//        em.createQuery("delete from AlojamientoEntity").executeUpdate();
//        em.createQuery("delete from ProveedorEntity").executeUpdate();
//    }
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     */
//    private void insertData() {
//        for (int i = 0; i < 3; i++) {
//            ProveedorEntity proveedor = factory.manufacturePojo(ProveedorEntity.class);
//            em.persist(proveedor);
//            proveedorData.add(proveedor);
//        }
//        
//        for (int i = 0; i < 3; i++) {
//            AlojamientoEntity entity = factory.manufacturePojo(AlojamientoEntity.class);
//            entity.setProveedor(proveedorData.get(0)); 
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Pruebas para crear un Alojamiento.
//     *
//     * @throws BusinessLogicException
//     */
//    //Prueba basica
//    @Test
//    public void createAlojamientoTest() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);   
//        
//        newEntity.setProveedor(proveedorData.get(0));
//        newEntity.setCosto(1.1);
//        newEntity.setEstrellas(4);
//        newEntity.setLatitud(4.8555555); 
//        newEntity.setLongitud(4.3265656);
//        newEntity.setNoches(1);
//        newEntity.setNombre("Funciono"); 
//        newEntity.setPuntuacion(1);
//        newEntity.setTipo("Hotel");
//        newEntity.setUbicacion("Bogota"); 
//        AlojamientoEntity result = alojamientoLogic.createAlojamiento(newEntity);
//       
//        Assert.assertNotNull(result); 
//        AlojamientoEntity entity = em.find(AlojamientoEntity.class, newEntity.getId());
//        Assert.assertEquals(newEntity.getId(), entity.getId()); 
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
//        Assert.assertEquals(newEntity.getEstrellas(), entity.getEstrellas()); 
//        Assert.assertEquals(newEntity.getNoches(), entity.getNoches());
//        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
//        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion()); 
//        
//    }
//
//    /**
//     * En caso que el proveedor del alojamiento sea null y/o vacio.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConProveedorInvalido() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setProveedor(null);  
//        alojamientoLogic.createAlojamiento(newEntity);
//    }
//    
//    /**
//     * En caso que el nombre del alojamiento sea null y/o vacio.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConNombreInvalido() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setNombre(""); 
//        alojamientoLogic.createAlojamiento(newEntity);
//    }
//
//    /**
//     * En caso que el nombre del alojamiento sea mayor a 25 caracteres.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConNombreInvalido2() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setNombre("rstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyz");
//        alojamientoLogic.createAlojamiento(newEntity);
//    }
//    
//    /**
//     * En caso que el costo del alojamiento sea menor a 0.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConCostoInvalido() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setCosto(0.0); 
//        alojamientoLogic.createAlojamiento(newEntity);
//    }
//    
//    /**
//     * En caso que las estrellas del alojamiento sean menor a 0.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConEstrellasInvalidas() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setEstrellas(-1); 
//        alojamientoLogic.createAlojamiento(newEntity);
//    }
//
//    /**
//     * En caso que las estrellas del alojamiento sean mayor a 5.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConEstrellasInvalidas2() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setEstrellas(6); 
//        alojamientoLogic.createAlojamiento(newEntity);
//    }
//    
//    /**
//     * En caso que las noches del alojamiento sean menor a 0.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConNochesInvalidas() throws BusinessLogicException {
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setNoches(-1); 
//        alojamientoLogic.createAlojamiento(newEntity);
//    }
//
//    /**
//     * Prueba para consultar la lista de Alojamientos.
//     */
//    @Test
//    public void getAlojamientosTest() {
//        
//        List<AlojamientoEntity> list = alojamientoLogic.getAlojamientos(); 
//        Assert.assertEquals(data.size(), list.size()); 
//        for (AlojamientoEntity ent : list) {
//            boolean found = false;
//            for (AlojamientoEntity entity : data) {
//                if (ent.getId().equals(entity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//
//    /**
//     * Prueba para consultar un Alojamiento.
//     *
//     */
//    @Test
//    public void getAlojamientoTest() {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = alojamientoLogic.getAlojamiento(entity.getId());
//       
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
//        Assert.assertEquals(newEntity.getEstrellas(), entity.getEstrellas());
//        Assert.assertEquals(newEntity.getNoches(), entity.getNoches());
//        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
//    }
//
//    /**
//     * Prueba para eliminar un Alojamiento.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test
//    public void deleteAlojamientoTest() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        alojamientoLogic.deleteAlojamiento(entity.getId());
//        AlojamientoEntity deleted = em.find(AlojamientoEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un Alojamiento.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test 
//    public void updateAlojamientoTest() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        entity.setNombre("Funciono");
//        entity.setCosto(1.1);
//        entity.setEstrellas(4);
//        entity.setLatitud(4.8555555); 
//        entity.setLongitud(4.3265656);
//        entity.setNoches(1);
//        entity.setPuntuacion(1);
//        entity.setTipo("Hotel");
//        entity.setUbicacion("Bogota");
//        alojamientoLogic.createAlojamiento(entity);  
//        
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setCosto(1.1);
//        newEntity.setEstrellas(3);
//        newEntity.setLatitud(4.8555555); 
//        newEntity.setLongitud(4.3265656);
//        newEntity.setNoches(1);
//        newEntity.setNombre(entity.getNombre());  
//        newEntity.setPuntuacion(1);
//        newEntity.setTipo("Hotel");
//        newEntity.setUbicacion("Bogota");
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(newEntity.getId(), newEntity);
//
//        AlojamientoEntity resp = em.find(AlojamientoEntity.class, entity.getId());
//
//        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
//        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
//        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
//        Assert.assertEquals(newEntity.getEstrellas(), resp.getEstrellas());
//        Assert.assertEquals(newEntity.getNoches(), resp.getNoches());
//        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), resp.getUbicacion()); 
//    }
//    
//    /**
//     * Prueba para actualizar un Alojamiento con nombre invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateAlojamientoTestConNombreInvalido() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        
//        newEntity.setNombre("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"); 
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(newEntity.getId(), newEntity);
//
//    }
//    
//    /**
//     * Prueba para actualizar un Alojamiento con nombre null.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateAlojamientoTestConNombreNull() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        
//        newEntity.setNombre("");  
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(newEntity.getId(), newEntity);
//    }
//    
//    /**
//     * Prueba para actualizar un Alojamiento con costo menor a 0.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateAlojamientoTestConCostoInvalido() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setCosto(-1.0); 
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(newEntity.getId(), newEntity);
//    }
//    
//     /**
//     * Prueba para actualizar un Alojamiento con estrellas menor a 0.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateAlojamientoTestConEstrellasInvalido() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setEstrellas(-2);  
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(newEntity.getId(), newEntity);
//    }
//     /**
//     * Prueba para actualizar un Alojamiento con estrellas mayor a 5.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateAlojamientoTestConEstrellasInvalido2() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setEstrellas(7);  
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(newEntity.getId(), newEntity);
//    }
//    
//     /**
//     * Prueba para actualizar un Alojamiento con estrellas mayor a 5.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateAlojamientoTestConNochesInvalido() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        newEntity.setNoches(-1);  
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(newEntity.getId(), newEntity);
//    }
}
