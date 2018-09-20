/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.persistence.EntradaPersistence;
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
public class EntradaPersistenceTest {
        /**
     * Inyección de la dependencia a la clase EntradaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EntradaPersistence entradaPersistence;

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
    private List<EntradaEntity> data = new ArrayList<EntradaEntity>();
    
        /**
     * Lista que tiene los datos de prueba de usuario.
     */
    private List<UsuarioEntity> dataUser = new ArrayList<UsuarioEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Entrada, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EntradaEntity.class.getPackage())
                .addPackage(EntradaPersistence.class.getPackage())
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
        em.createQuery("delete from EntradaEntity").executeUpdate();
        em.createQuery("delete from UserEntity").executeUpdate();
    }
    
        /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            dataUser.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            EntradaEntity entity = factory.manufacturePojo(EntradaEntity.class);
            if (i == 0) {
                entity.setAutor(dataUser.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Entrada.
     */
    @Test
    public void createEntradaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EntradaEntity newEntity = factory.manufacturePojo(EntradaEntity.class);
        EntradaEntity result = entradaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EntradaEntity entity = em.find(EntradaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getAutor(), entity.getAutor());
        Assert.assertEquals(newEntity.getComentarios(), entity.getComentarios());
        Assert.assertEquals(newEntity.getCombo(), entity.getCombo());
        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
        Assert.assertEquals(newEntity.getTextoContenido(), entity.getTextoContenido());
        Assert.assertEquals(newEntity.getMultimedia(), entity.getMultimedia());
        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion(), 0);
        Assert.assertEquals(newEntity.getCalificacionComunidad(), entity.getCalificacionComunidad(), 0);
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
    }
    
        /**
     * Prueba para consultar la lista de Entradas.
     *
     *
     */
    @Test
    public void getEntradasTest() {
        List<EntradaEntity> list = entradaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EntradaEntity ent : list) {
            boolean found = false;
            for (EntradaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Entrada.
     *
     *
     */
    @Test
        public void getEntradaTest() {
        EntradaEntity entity = data.get(0);
        EntradaEntity newEntity = entradaPersistence.find(dataUser.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar una Entrada.
     *
     *
     */
    @Test
    public void deleteEntradaTest() {
        EntradaEntity entity = data.get(0);
        entradaPersistence.delete(entity.getId());
        EntradaEntity deleted = em.find(EntradaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Entrada.
     *
     *
     */
    @Test
    public void updateEntradaTest() {
        EntradaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EntradaEntity newEntity = factory.manufacturePojo(EntradaEntity.class);

        newEntity.setId(entity.getId());

        entradaPersistence.update(newEntity);

        EntradaEntity resp = em.find(EntradaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

}
