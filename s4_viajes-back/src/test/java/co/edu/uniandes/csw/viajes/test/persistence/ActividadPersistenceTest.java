/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class ActividadPersistenceTest {
    
    

    @Inject
    private ActividadPersistence actividadPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ActividadEntity> data = new LinkedList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
                .addPackage(ActividadPersistence.class.getPackage())
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
        em.createQuery("delete from ActividadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Author.
     */
    @Test
    public void createActividadTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        ActividadEntity result = actividadPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Authors.
     */
    @Test
    public void getAllActividadTest() {
        List<ActividadEntity> list = actividadPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ActividadEntity ent : list) {
            boolean found = false;
            for (ActividadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Author.
     */
    @Test
    public void getActividadTest() {
        ActividadEntity entity = data.get(0);
        ActividadEntity newEntity = actividadPersistence.find(entity.getIdentificador());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getCosto(), newEntity.getCosto());
    }

    /**
     * Prueba para actualizar un Author.
     */
    @Test
    public void updateActividadTest() {
        ActividadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);

        newEntity.setId(entity.getId());

        actividadPersistence.update(newEntity);

        ActividadEntity resp = em.find(ActividadEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
    }

    /**
     * Prueba para eliminar un Author.
     */
    @Test
    public void deleteActividadTest() {
        ActividadEntity entity = data.get(0);
        actividadPersistence.delete(entity.getIdentificador());
        ActividadEntity deleted = em.find(ActividadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
