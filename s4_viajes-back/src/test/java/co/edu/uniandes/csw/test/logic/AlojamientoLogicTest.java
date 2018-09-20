///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.csw.test.logic;
//
//import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
//import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
//import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
//import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
//import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
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
//public class AlojamientoLogicTest {
//
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
//    private List<AlojamientoEntity> data = new ArrayList<AlojamientoEntity>();
//    
//    private List<ProveedorEntity> proveedorData = new ArrayList<ProveedorEntity>();
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
//        em.createQuery("delete from ProveedorEntity").executeUpdate();
//        em.createQuery("delete from AlojamientoEntity").executeUpdate();
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
//            entity.setProveedor(proveedorData.get(1)); 
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
//        newEntity.setProveedor(proveedorData.get(1));
//        newEntity.setCosto(1.1);
//        newEntity.setEstrellas(4);
//        newEntity.setLatitud(4.8555555); 
//        newEntity.setLongitud(4.3265656);
//        newEntity.setNoches(1);
//        newEntity.setNombre("Funciono"); 
//        newEntity.setPuntuacion(1);
//        newEntity.setTipo("Hotel");
//        newEntity.setUbicacion("Bogota"); 
//        
//        
//        AlojamientoEntity result = alojamientoLogic.createAlojamiento(proveedorData.get(1).getId(), newEntity);
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
////    /**
////     * En caso que el nombre del alojamiento sea null y/o vacio.
////     *
////     * @throws BusinessLogicException
////     */
////    @Test(expected = BusinessLogicException.class)
////    public void createAlojamientoTest2() throws BusinessLogicException {
////        PodamFactory factory = new PodamFactoryImpl();
////        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
////        newEntity.setNombre(null);
////
////        alojamientoLogic.createAlojamiento(newEntity);
////
////    }
////
////    /**
////     * En caso que el nombre del alojamiento sea mayor a 25 caracteres.
////     *
////     * @throws BusinessLogicException
////     */
////    @Test(expected = BusinessLogicException.class)
////    public void createAlojamientoTest3() throws BusinessLogicException {
////        PodamFactory factory = new PodamFactoryImpl();
////        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
////        newEntity.setNombre("rstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyz");
////
////        alojamientoLogic.createAlojamiento(newEntity);
////
////    }
//
//    /**
//     * Prueba para consultar la lista de Alojamientos.
//     */
//    @Test
//    public void getAlojamientosTest() {
//        
//        List<AlojamientoEntity> list = alojamientoLogic.getAlojamientos(proveedorData.get(1).getId()); 
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
//     * @throws BusinessLogicException
//     */
//    @Test
//    public void getAlojamientoTest() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = alojamientoLogic.getAlojamiento(proveedorData.get(1).getId(), entity.getId());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
//        Assert.assertEquals(newEntity.getProveedor(), entity.getProveedor());
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
//        Assert.assertEquals(newEntity.getEstrellas(), entity.getEstrellas());
//        Assert.assertEquals(newEntity.getNoches(), entity.getNoches());
//        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
//    }
//
////    /**
////     * Prueba para consultar un Alojamiento con id invalido.
////     *
////     * @throws BusinessLogicException
////     */
////    @Test(expected = BusinessLogicException.class)
////    public void getAlojamientoTest2() throws BusinessLogicException {
////        AlojamientoEntity entity = data.get(0);
////        entity.setId(null);
////        AlojamientoEntity newEntity = alojamientoLogic.getAlojamiento(entity.getId());
////
////    }
//
//    /**
//     * Prueba para eliminar un Alojamiento.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test
//    public void deleteAlojamientoTest() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        alojamientoLogic.deleteAlojamiento(proveedorData.get(1).getId() , entity.getId());
//        AlojamientoEntity deleted = em.find(AlojamientoEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para eliminar un Alojamiento con id invalido.
//     *
//     * @throws BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class) 
//    public void deleteAlojamientoTest2() throws BusinessLogicException {
//        AlojamientoEntity entity = data.get(0);
//        alojamientoLogic.deleteAlojamiento(proveedorData.get(0).getId() , entity.getId());
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
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        
//        newEntity.setId(entity.getId());
//
//        alojamientoLogic.updateAlojamiento(proveedorData.get(1).getId(), newEntity);
//
//        AlojamientoEntity resp = em.find(AlojamientoEntity.class, entity.getId());
//
//        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
//        Assert.assertEquals(newEntity.getProveedor(), resp.getProveedor());
//        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
//        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
//        Assert.assertEquals(newEntity.getEstrellas(), resp.getEstrellas());
//        Assert.assertEquals(newEntity.getLatitud(), resp.getLatitud());
//        Assert.assertEquals(newEntity.getLongitud(), resp.getLongitud());
//        Assert.assertEquals(newEntity.getNoches(), resp.getNoches());
//        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), resp.getUbicacion()); 
//    }
//}
