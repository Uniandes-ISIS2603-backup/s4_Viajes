/*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.csw.test.logic;
//
//import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
//import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
//import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
///**
// *
// * @author Ymespana
// */
//@RunWith(Arquillian.class)
//public class TransporteTerrestreLogicTest {
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
//    private List<TransporteTerrestreEntity> data = new ArrayList<TransporteTerrestreEntity>();
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
//    }
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     */
//    private void insertData() {
//        PodamFactory factory = new PodamFactoryImpl();
//        for (int i = 0; i < 3; i++) {
//            TransporteTerrestreEntity entity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//
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
//        PodamFactory factory = new PodamFactoryImpl();
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//
//        TransporteTerrestreLogic.createTransporteTerrestre(newEntity);
//
//        TransporteTerrestreEntity entity = em.find(TransporteTerrestreEntity.class, newEntity.getId());
//
//        Assert.assertNotNull(entity); 
//        
//    }
//
//    /**
//     * En caso que el id del TransporteTerrestre sea null y/o vacio.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createAlojamientoTest2() throws BusinessLogicException {
//        PodamFactory factory = new PodamFactoryImpl();
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        newEntity.setId(null);
//
//        transporteTerrestreLogic.createTransporteTerrestre(newEntity);
//
//    }
//
//    /**
//     * En caso que el costo del TransporteTerrestre sea menor a 0.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createTransporteTerrestreTest4() throws BusinessLogicException {
//        PodamFactory factory = new PodamFactoryImpl();
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//        newEntity.setCosto(-5.00);
//
//        transporteTerrestreLogic.createTransporteTerrestre(newEntity);
//
//    }

//
//    /**
//     * Prueba para consultar la lista de Alojamientos.
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
//
//        AlojamientoEntity newEntity = alojamientoLogic.getAlojamiento(entity.getId());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getProveedor(), entity.getProveedor());
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
//        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
//        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
//    }
//
//    /**
//     * Prueba para consultar un TransporteTerrestre con id invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void getTransporteTerrestreTest2() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        entity.setId(null);
//        TransporteTerrestreEntity newEntity = transporteTerrestreLogic.getTransporteTerrestre(entity.getId());
//
//    }
//
//    /**
//     * Prueba para eliminar un TransporteTerrestre.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test
//    public void deleteAlojamientoTest() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        transporteTerrestreLogic.deleteTransporte(entity.getId());
//        TransporteTerrestreEntity deleted = em.find(TransporteTerrestreEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para eliminar un TransporteTerrestre con id invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class) 
//    public void deleteTransporteTerrestreTest2() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        entity.setId(null);
//        transporteTerrestreLogic.deleteTransporteTerrestre(entity.getId());
//        TransporteTerrestreEntity deleted = em.find(TransporteTerrestreEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un TransporteTerrestre.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test
//    public void updateTransporteTerrestreTest() throws BusinessLogicException {
//        TransporteTerrestreEntity entity = data.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
//
//        newEntity.setId(entity.getId());
//
//        transporteTerrestreLogic.updateTransporteTerrestre(newEntity.getId(), newEntity);
//
//        TransporteTerrestreEntity resp = em.find(TransporteTerrestreEntity.class, entity.getId());
//
//        Assert.assertEquals(newEntity.getProveedor(), resp.getProveedor());
//        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
//        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
//        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
//    }
//}
