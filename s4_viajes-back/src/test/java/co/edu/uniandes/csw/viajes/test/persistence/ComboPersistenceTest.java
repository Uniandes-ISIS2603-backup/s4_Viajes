/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
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

public class ComboPersistenceTest {
     @Inject
    private ComboPersistence comboPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ComboEntity> data = new ArrayList<ComboEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComboEntity.class.getPackage())
                .addPackage(ComboPersistence.class.getPackage())
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
        em.createQuery("delete from ComboEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ComboEntity entity = factory.manufacturePojo(ComboEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Editorial.
     */
    @Test
    public void createComboTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        ComboEntity result = comboPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ComboEntity entity = em.find(ComboEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Editoriales.
     */
    @Test
    public void getCombosTest() {
        List<ComboEntity> list = comboPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ComboEntity ent : list) {
            boolean found = false;
            for (ComboEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Editorial.
     */
    @Test
    public void getComboTest() {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = comboPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para eliminar una Editorial.
     */
    @Test
    public void deleteComboTest() {
        ComboEntity entity = data.get(0);
        comboPersistence.delete(entity.getId());
        ComboEntity deleted = em.find(ComboEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Editorial.
     */
    @Test
    public void updateComboTest() {
        ComboEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);

        newEntity.setId(entity.getId());

        comboPersistence.update(newEntity);

        ComboEntity resp = em.find(ComboEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para consultar una Editorial por nombre.
     */
    @Test
    public void findComboByNombreTest() {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = comboPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = comboPersistence.findByName(null);
        Assert.assertNull(newEntity);
    }
}
