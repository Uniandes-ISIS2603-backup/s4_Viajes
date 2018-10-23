/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.logic;

import co.edu.uniandes.csw.viajes.ejb.MedallaLogic;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
public class MedallaLogicTest {
        private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MedallaLogic medallaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MedallaEntity> data = new ArrayList<MedallaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedallaEntity.class.getPackage())
                .addPackage(MedallaLogic.class.getPackage())
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
        for (int i = 0; i < 3; i++) {
            MedallaEntity entity = factory.manufacturePojo(MedallaEntity.class);  
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Medalla
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createMedallaTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);

        newEntity.setNombre("Medalla");
        newEntity.setDescripcion("Es una medalla de prueba");
        newEntity.setRutaImagen("medalla.png");
        MedallaEntity result = medallaLogic.createMedalla(newEntity);
        
        Assert.assertNotNull(result);
        MedallaEntity entity = em.find(MedallaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
    }

    /**
     * Prueba para crear una Medalla con el mismo nombre de una Medalla que ya
     * existe.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConMismoNombreTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre(data.get(0).getNombre());  
        newEntity.setDescripcion("Es una medalla de prueba");
        newEntity.setRutaImagen("medalla.png");
        medallaLogic.createMedalla(newEntity);

    }
    
     /**
     * Prueba para crear una Medalla con el nombre nulo
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConNombreNuloTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre(null);
        newEntity.setDescripcion("Es una medalla de prueba");
        newEntity.setRutaImagen("medalla.png");
        medallaLogic.createMedalla(newEntity);
    }
    
    /**
     * Prueba para crear una Medalla con el nombre vacío
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConNombreVacioTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("");
        newEntity.setDescripcion("Es una medalla de prueba");
        newEntity.setRutaImagen("medalla.png");
        medallaLogic.createMedalla(newEntity);
    }
    
    /**
     * Prueba para crear una Medalla que incumple con la expresión regular definida para el nombre.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConNombreQueIncumpleLaExpReg() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("Este nombre debe exceder los 25 caracteres para incumplir la expresion regular que evalua el nombre");
        newEntity.setDescripcion("Es una medalla de prueba");
        newEntity.setRutaImagen("medalla.png");
        medallaLogic.createMedalla(newEntity);
    }
    
     /**
     * Prueba para crear una Medalla con la descripción nula
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConDescripcionNulaTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("Medalla");
        newEntity.setDescripcion(null);
        newEntity.setRutaImagen("medalla.png");
        medallaLogic.createMedalla(newEntity);
    }
    
    /**
     * Prueba para crear una Medalla con el nombre vacío
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConDescripcionVaciaTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("Medalla");
        newEntity.setDescripcion("");
        newEntity.setRutaImagen("medalla.png");
        medallaLogic.createMedalla(newEntity);
    }
    
    /**
     * Prueba para crear una Medalla que incumple con la expresión regular definida para la descripción.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConDescripcionQueIncumpleLaExpReg() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("Medalla");
        newEntity.setDescripcion("Ha");
        newEntity.setRutaImagen("medalla.png");
        medallaLogic.createMedalla(newEntity);
    }
    
    /**
     * Prueba para crear una Medalla con la ruta de imagen nula
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConImagenNulaTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("Medalla");
        newEntity.setDescripcion("Es una medalla de prueba");
        newEntity.setRutaImagen(null);
        medallaLogic.createMedalla(newEntity);
    }
    
    /**
     * Prueba para crear una Medalla con la ruta de imagen vacía
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConImagenVaciaTest() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("Medalla");
        newEntity.setDescripcion("Es una medalla de prueba");
        newEntity.setRutaImagen("");
        medallaLogic.createMedalla(newEntity);
    }
    
    /**
     * Prueba para crear una Medalla que incumple con la expresión regular definida para la ruta de imagen.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createMedallaConImagenQueIncumpleLaExpReg() throws BusinessLogicException {
        MedallaEntity newEntity = factory.manufacturePojo(MedallaEntity.class);
        newEntity.setNombre("Medalla");
        newEntity.setDescripcion("Ha");
        newEntity.setRutaImagen("Este_nombre_debe_exceder_los_30_caracteres_para_incumplir_la_expresion_regular_que_evalua_la_ruta_de_imagen.png");
        medallaLogic.createMedalla(newEntity);
    }
    

    /**
     * Prueba para consultar la lista de Medallas.
     */
    @Test
    public void getMedallasTest() {
        List<MedallaEntity> list = medallaLogic.getMedallas();
        Assert.assertEquals(data.size(), list.size());
        for (MedallaEntity entity : list) {
            boolean found = false;
            for (MedallaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Medalla.
     */
    @Test
    public void getMedallaTest() {
        MedallaEntity entity = data.get(0);
        MedallaEntity resultEntity = medallaLogic.getMedalla(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(entity.getRutaImagen(), entity.getRutaImagen()); 
    }

    /**
     * Prueba para actualizar una Medalla.
     */
    @Test
    public void updateMedallaTest() throws BusinessLogicException{
        MedallaEntity entity = data.get(0);
        MedallaEntity pojoEntity = factory.manufacturePojo(MedallaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNombre("Medalla");
        pojoEntity.setDescripcion("Es una medalla de prueba");
        pojoEntity.setRutaImagen("medalla.png");
        medallaLogic.updateMedalla(pojoEntity.getId(), pojoEntity);
        MedallaEntity resp = em.find(MedallaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getRutaImagen(), resp.getRutaImagen());     
    }

    /**
     * Prueba para eliminar una Medalla.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteMedallaTest() throws BusinessLogicException {
        MedallaEntity entity = data.get(1);
        medallaLogic.deleteMedalla(entity.getId());
        MedallaEntity deleted = em.find(MedallaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
