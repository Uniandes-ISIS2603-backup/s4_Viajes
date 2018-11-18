package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ymespana
 */
@RunWith(Arquillian.class)
public class AlojamientoPersistenceTest {

//    @Inject
//    private AlojamientoPersistence alojamientoPersistence;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    private List<AlojamientoEntity> data = new ArrayList<>();
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
//    }
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     */
//    private void insertData() {
//        PodamFactory factory = new PodamFactoryImpl();
//        for (int i = 0; i < 3; i++) {
//            AlojamientoEntity entity = factory.manufacturePojo(AlojamientoEntity.class);
//
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Prueba para crear un Alojamiento.
//     */
//    @Test
//    public void createAlojamientoTest() {
//        PodamFactory factory = new PodamFactoryImpl();
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//        AlojamientoEntity result = alojamientoPersistence.create(newEntity);
//
//        Assert.assertNotNull(result);
//
//        AlojamientoEntity entity = em.find(AlojamientoEntity.class, result.getId());
//
//        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto(), 0); 
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion(), 0);
//        Assert.assertEquals(newEntity.getEstrellas(), entity.getEstrellas(), 0);
//        Assert.assertEquals(newEntity.getNoches(), entity.getNoches(), 0); 
//        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
//        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud(), 0);
//        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud(), 0);
//    }
//
//    /**
//     * Prueba para consultar la lista de Alojamientos.
//     */
//    @Test
//    public void getAlojamientosTest() {
//        List<AlojamientoEntity> list = alojamientoPersistence.findAll();
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
//     */
//    @Test
//    public void getAlojamientoTest() {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = alojamientoPersistence.find(entity.getId());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
//        Assert.assertEquals(newEntity.getCosto(), entity.getCosto(), 0);
//        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion(), 0);
//        Assert.assertEquals(newEntity.getEstrellas(), entity.getEstrellas(), 0);
//        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud(), 0);
//        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud(), 0);
//        Assert.assertEquals(newEntity.getNoches(), entity.getNoches(), 0);
//        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
//    }
//
//    /**
//     * Prueba para eliminar un Alojamiento.
//     */
//    @Test
//    public void deleteAlojamientoTest() {
//        AlojamientoEntity entity = data.get(0);
//        alojamientoPersistence.delete(entity.getId());
//        AlojamientoEntity deleted = em.find(AlojamientoEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un Alojamiento.
//     */
//    @Test
//    public void updateAlojamientoTest() {
//        AlojamientoEntity entity = data.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        AlojamientoEntity newEntity = factory.manufacturePojo(AlojamientoEntity.class);
//
//        newEntity.setId(entity.getId());
//
//        alojamientoPersistence.update(newEntity);
//
//        AlojamientoEntity resp = em.find(AlojamientoEntity.class, entity.getId());
//
//        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
//        Assert.assertEquals(newEntity.getCosto(), resp.getCosto(), 0);
//        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion(), 0);
//        Assert.assertEquals(newEntity.getEstrellas(), resp.getEstrellas(), 0);
//        Assert.assertEquals(newEntity.getNoches(), resp.getNoches(), 0);
//        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
//        Assert.assertEquals(newEntity.getUbicacion(), resp.getUbicacion());
//    }
//
//    /**
//     * Prueba para consultasr un Alojamiento por Nombre.
//     */
//    @Test
//    public void findAlojamientoByNombreTest() {
//        AlojamientoEntity entity = data.get(0);
//        AlojamientoEntity newEntity = alojamientoPersistence.findByNombre(entity.getNombre());
//        Assert.assertNotNull(newEntity);
//
//        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
//
//        newEntity = alojamientoPersistence.findByNombre(null);
//        Assert.assertNull(newEntity);
//    }

}
