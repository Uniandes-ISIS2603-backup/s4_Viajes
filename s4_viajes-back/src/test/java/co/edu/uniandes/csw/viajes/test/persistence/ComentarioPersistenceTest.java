/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.ComentarioEntity;
import co.edu.uniandes.csw.viajes.persistence.ComentarioPersistence;
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
public class ComentarioPersistenceTest {
            /**
     * Inyección de la dependencia a la clase ComentarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ComentarioPersistence comentarioPersistence;

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
    private List<ComentarioEntity> data = new ArrayList<ComentarioEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Entrada, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
    }
    
        /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un comentario.
     */
    @Test
    public void createComentarioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result = comentarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ComentarioEntity entity = em.find(ComentarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(newEntity.getTextoContenido(), entity.getTextoContenido());
        Assert.assertEquals(newEntity.getMultimedia(), entity.getMultimedia());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion(), 0);
        Assert.assertEquals(newEntity.getFechaPub(), entity.getFechaPub());
    }
    
        /**
     * Prueba para consultar la lista de Comentarios.
     *
     *
     */
    @Test
    public void getComentariosTest() {
        List<ComentarioEntity> list = comentarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ComentarioEntity ent : list) {
            boolean found = false;
            for (ComentarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Comentario.
     *
     *
     */
    @Test
        public void getComentarioTest() {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity newEntity = comentarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar un Comentario.
     *
     *
     */
    @Test
    public void deleteComentarioTest() {
        ComentarioEntity entity = data.get(0);
        comentarioPersistence.delete(entity.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Comentario.
     *
     *
     */
    @Test
    public void updateComentarioTest() {
        ComentarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);

        newEntity.setId(entity.getId());

        comentarioPersistence.update(newEntity);

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumero(), resp.getNumero());
    }
}
