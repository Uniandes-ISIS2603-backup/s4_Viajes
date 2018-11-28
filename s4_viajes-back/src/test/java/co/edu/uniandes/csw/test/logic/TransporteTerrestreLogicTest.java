/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.test.logic;

import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
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
public class TransporteTerrestreLogicTest {
//
//    private PodamFactory factory = new PodamFactoryImpl();
//    
//    @Inject
//    private TransporteTerrestreLogic transporteTerrestreLogic;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    private List<TransporteTerrestreEntity> data = new ArrayList<>();
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
//                .addPackage(TransporteTerrestreEntity.class.getPackage())
//                .addPackage(TransporteTerrestreLogic.class.getPackage())
//                .addPackage(TransporteTerrestrePersistence.class.getPackage())
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
//        em.createQuery("delete from TransporteTerrestreEntity").executeUpdate();
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
//            TransporteTerrestreEntity entity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//            entity.setProveedor(proveedorData.get(0)); 
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Pruebas para crear un TransporteTerrestre.
//     *
//     * @throws BusinessLogicException
//     */
//    //Prueba basica
//    @Test
//    public void createTransporteTerrestreTest() throws BusinessLogicException {
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setCosto(1.1);
//        newEntity.setDestino("Bogota");
//        newEntity.setLatitudDestino(4.85);
//        newEntity.setLatitudOrigen(5.85);
//        newEntity.setLongitudDestino(3.52);
//        newEntity.setLongitudOrigen(2.52);
//        newEntity.setNumeroDias(1);
//        newEntity.setNumeroHoras(1);
//        newEntity.setNumeroMinutos(0);
//        newEntity.setPuntuacion(5); 
//        
//        TransporteTerrestreEntity result = transporteTerrestreLogic.createTransporte(newEntity); 
//        Assert.assertNotNull(result); 
//        
//        TransporteTerrestreEntity entity = em.find(TransporteTerrestreEntity.class, newEntity.getId());
//        Assert.assertEquals(newEntity.getId(), entity.getId()); 
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
//        Assert.assertEquals(newEntity.getDestino(), entity.getDestino()); 
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
//        Assert.assertEquals(newEntity.getNumeroDias(), entity.getNumeroDias());
//        Assert.assertEquals(newEntity.getNumeroHoras(), entity.getNumeroHoras());
//        Assert.assertEquals(newEntity.getNumeroMinutos(), entity.getNumeroMinutos());
//    }
//
//    /**
//     * En caso que el provedor sea invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTestConProveedorInvalido() throws BusinessLogicException {
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        newEntity.setProveedor(null); 
//        transporteTerrestreLogic.createTransporte(newEntity); 
//
//    }
//
//    /**
//     * En caso que el costo del TransporteTerrestre sea menor a 0.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createTransporteTerrestreTestConCostoInvalido() throws BusinessLogicException {
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setCosto(-5.00);
//        transporteTerrestreLogic.createTransporte(newEntity);
//
//    }
//    
//     /**
//     * En caso que el destino del TransporteTerrestre sea invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createTransporteTerrestreTestConDestinoInvalido() throws BusinessLogicException {
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setDestino(null); 
//        transporteTerrestreLogic.createTransporte(newEntity);
//
//    }
//    
//     /**
//     * En caso que la duracion del TransporteTerrestre sea invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createTransporteTerrestreTestConDuracionInvalido() throws BusinessLogicException {
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        newEntity.setProveedor(proveedorData.get(0)); 
//        newEntity.setNumeroDias(-1);
//        newEntity.setNumeroHoras(-1);
//        newEntity.setNumeroMinutos(-1); 
//        transporteTerrestreLogic.createTransporte(newEntity);
//
//    }
//
//    /**
//     * Prueba para consultar la lista de transportes.
//     */
//    @Test
//    public void getTransportesTest() {
//        List<TransporteTerrestreEntity> list = transporteTerrestreLogic.getTransportes();
//        Assert.assertEquals(data.size(), list.size());
//        for (TransporteTerrestreEntity ent : list) {
//            boolean found = false;
//            for (TransporteTerrestreEntity entity : data) {
//                if (ent.getId().equals(entity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//
//    /**
//     * Prueba para consultar un TransporteTerrestre.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test
//    public void getTransporteTerrestreTest() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        TransporteTerrestreEntity newEntity = transporteTerrestreLogic.getTransporte(entity.getId());
//
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
//        Assert.assertEquals(newEntity.getDestino(), entity.getDestino()); 
//        Assert.assertEquals(newEntity.getNumeroDias(), entity.getNumeroDias());
//        Assert.assertEquals(newEntity.getNumeroHoras(), entity.getNumeroHoras());
//        Assert.assertEquals(newEntity.getNumeroMinutos(), entity.getNumeroMinutos());
//    }
//
//    /**
//     * Prueba para eliminar un TransporteTerrestre.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test
//    public void deleteTransporteTest() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        transporteTerrestreLogic.deleteTransporte(entity.getId());
//        TransporteTerrestreEntity deleted = em.find(TransporteTerrestreEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un TransporteTerrestre.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test (expected = BusinessLogicException.class)
//    public void updateTransporteTerrestreTest() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        entity.setCosto(1.1);
//        entity.setDestino("Bogota");
//        entity.setLatitudDestino(4.85);
//        entity.setLatitudOrigen(5.85);
//        entity.setLongitudDestino(3.52);
//        entity.setLongitudOrigen(2.52);
//        entity.setNumeroDias(1);
//        entity.setNumeroHoras(1);
//        entity.setNumeroMinutos(0);
//        entity.setPuntuacion(5); 
//        transporteTerrestreLogic.createTransporte(entity);
//       
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        newEntity.setCosto(1.1);
//        newEntity.setDestino("Bogota");
//        newEntity.setLatitudDestino(4.85);
//        newEntity.setLatitudOrigen(5.85);
//        newEntity.setLongitudDestino(3.52);
//        newEntity.setLongitudOrigen(2.52);
//        newEntity.setNumeroDias(2);
//        newEntity.setNumeroHoras(1);
//        newEntity.setNumeroMinutos(0);
//        newEntity.setPuntuacion(5); 
//
//        transporteTerrestreLogic.updateTransporte(newEntity.getId(), newEntity);
//        TransporteTerrestreEntity resp = em.find(TransporteTerrestreEntity.class, entity.getId());
//        
//        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
//        Assert.assertEquals(newEntity.getDestino(), resp.getDestino()); 
//        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
//        Assert.assertEquals(newEntity.getNumeroDias(), resp.getNumeroDias());
//        Assert.assertEquals(newEntity.getNumeroHoras(), resp.getNumeroHoras());
//        Assert.assertEquals(newEntity.getNumeroMinutos(), resp.getNumeroMinutos());
//    }
//    
//    /**
//     * Prueba para actualizar un transporte con costo invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateTransporteTestConCostoInvalido() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        
//        newEntity.setCosto(-50.0); 
//        newEntity.setId(entity.getId());
//
//        transporteTerrestreLogic.updateTransporte(newEntity.getId(), newEntity);
//
//    }
//    
//    /**
//     * Prueba para actualizar un transporte con destino invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateTransporteTestConDestinoInvalido() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        
//        newEntity.setDestino(null); 
//        newEntity.setId(entity.getId());
//
//        transporteTerrestreLogic.updateTransporte(newEntity.getId(), newEntity);
//    }
//    
//    /**
//     * Prueba para actualizar un transporte con duracion invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void updateTransporteTestConDuracionInvalido() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        
//        newEntity.setNumeroDias(-1);
//        newEntity.setNumeroHoras(-1);
//        newEntity.setNumeroMinutos(-1); 
//        newEntity.setId(entity.getId());
//
//        transporteTerrestreLogic.updateTransporte(newEntity.getId(), newEntity);
//
//    }
}