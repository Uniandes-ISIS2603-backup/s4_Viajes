/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
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
 * Pruebas de persistencia de Proveedor
 *
 * @author jf.torresp
 */
@RunWith(Arquillian.class)
public class ProveedorPersistenceTest {
        /**
     * Inyección de la dependencia a la clase ProveedorPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ProveedorPersistence proveedorPersistence;

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
    private List<ProveedorEntity> data = new ArrayList<ProveedorEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
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
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Editorial.
     */
    @Test
    public void createEditorialTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        ProveedorEntity result = proveedorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ProveedorEntity entity = em.find(ProveedorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(newEntity.getPassword(), newEntity.getPassword());
        Assert.assertEquals(newEntity.getPuntaje(), newEntity.getPuntaje());
        Assert.assertEquals(newEntity.getUser(), entity.getUser());
    }

    /**
     * Prueba para consultar la lista de Editoriales.
     *
     *
     */
    @Test
    public void getEditorialsTest() {
        List<ProveedorEntity> list = proveedorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ProveedorEntity ent : list) {
            boolean found = false;
            for (ProveedorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Editorial.
     *
     *
     */
    @Test
    public void getEditorialTest() {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity newEntity = proveedorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getUser(), newEntity.getUser());
    }

    /**
     * Prueba para eliminar una Editorial.
     *
     *
     */
    @Test
    public void deleteEditorialTest() {
        ProveedorEntity entity = data.get(0);
        proveedorPersistence.delete(entity.getId());
        ProveedorEntity deleted = em.find(ProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Editorial.
     *
     *
     */
    @Test
    public void updateEditorialTest() {
        ProveedorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);

        newEntity.setId(entity.getId());

        proveedorPersistence.update(newEntity);

        ProveedorEntity resp = em.find(ProveedorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getUser(), resp.getUser());
    }

    /**
     * Prueba para consultar una Editorial por nombre.
     *
     *
     */
    @Test
    public void findEditorialByNameTest() {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity newEntity = proveedorPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = proveedorPersistence.findByName(null);
        Assert.assertNull(newEntity);
        }
}
