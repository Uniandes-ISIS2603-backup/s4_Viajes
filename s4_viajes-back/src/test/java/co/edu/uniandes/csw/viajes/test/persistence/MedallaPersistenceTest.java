/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.persistence.MedallaPersistence;
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
 * @author Luis Gómez Amado
 */
@RunWith(Arquillian.class)
public class MedallaPersistenceTest {
        
    /**
     * Inyección de la dependencia a la clase MedallaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private MedallaPersistence medallaPersistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<MedallaEntity> data = new ArrayList<MedallaEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Vuelo, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedallaEntity.class.getPackage())
                .addPackage(MedallaPersistence.class.getPackage())
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
        em.createQuery("delete from MedallaEntity").executeUpdate();
    }
    
        /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            MedallaEntity entity = factory.manufacturePojo(MedallaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Medalla.
     */
    @Test
    public void createMedallaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        MedallaEntity result = medallaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedallaEntity entity = em.find(MedallaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    
        /**
     * Prueba para consultar la lista de Medallas.
     *
     *
     */
    @Test
    public void getMedallasTest() {
        List<MedallaEntity> list = medallaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MedallaEntity ent : list) {
            boolean found = false;
            for (MedallaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Medalla.
     *
     *
     */
    @Test
        public void getMedallaTest() {
        MedallaEntity entity = data.get(0);
        MedallaEntity newEntity = medallaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar una Medalla.
     *
     *
     */
    @Test
    public void deleteVueloTest() {
        MedallaEntity entity = data.get(0);
        medallaPersistence.delete(entity.getId());
        MedallaEntity deleted = em.find(MedallaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Medalla.
     *
     *
     */
    @Test
    public void updateMedallaTest() {
        MedallaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);

        newEntity.setId(entity.getId());

        medallaPersistence.update(newEntity);

        MedallaEntity resp = em.find(MedallaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

        /**
     * Prueba para buscar una Medalla por nombre.
     *
     *
     */
    @Test
    public void findMedallaByNameTest(){
        MedallaEntity entity = data.get(0);
        MedallaEntity newEntity = medallaPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
}
