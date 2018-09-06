/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.persistence.GuiaPersistence;
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
public class GuiaPersistenceTest {
    
    @Inject
    private GuiaPersistence guiaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<GuiaEntity> data = new LinkedList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(GuiaPersistence.class.getPackage())
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
        em.createQuery("delete from GuiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            GuiaEntity entity = factory.manufacturePojo(GuiaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Guia.
     */
    @Test
    public void createGuiaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        GuiaEntity result = guiaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        GuiaEntity entity = em.find(GuiaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
        Assert.assertEquals(newEntity.getSueldo(), entity.getSueldo());
    }

    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getAllGuiaTest() {
        List<GuiaEntity> list = guiaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (GuiaEntity ent : list) {
            boolean found = false;
            for (GuiaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getBookTest() {
        GuiaEntity entity = data.get(0);
        GuiaEntity newEntity = guiaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDocumento(), newEntity.getDocumento());
        Assert.assertEquals(entity.getSueldo(), newEntity.getSueldo());
        Assert.assertEquals(entity.getPuntuacion(), newEntity.getPuntuacion());
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para eliminar un Book.
     */
    @Test
    public void deleteGuiaTest() {
        GuiaEntity entity = data.get(0);
        guiaPersistence.delete(entity.getId());
        GuiaEntity deleted = em.find(GuiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Guia.
     */
    @Test
    public void updateGuiaTest() {
        GuiaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);

        newEntity.setId(entity.getId());

        guiaPersistence.update(newEntity);

        GuiaEntity resp = em.find(GuiaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getSueldo(), resp.getSueldo());
        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
        Assert.assertEquals(newEntity.getDocumento(), resp.getDocumento());
    }

    
}
