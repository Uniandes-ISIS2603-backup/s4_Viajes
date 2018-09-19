/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.persistence;

import co.edu.uniandes.csw.viajes.ejb.ComboLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
public class ComboLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ComboLogic comboLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ComboEntity> data = new ArrayList<ComboEntity>();

    private List<ActividadEntity> actividadesData = new ArrayList();
    
    private List<VueloEntity> vuelosData = new ArrayList();
    
    private List<AlojamientoEntity> alojamientosData = new ArrayList();
    
    private List<TransporteTerrestreEntity> transportesTerrestresData = new ArrayList();

    
    

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComboEntity.class.getPackage())
                .addPackage(ComboLogic.class.getPackage())
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
        em.createQuery("delete from VueloEntity").executeUpdate();
        em.createQuery("delete from AlojamientoEntity").executeUpdate();
        em.createQuery("delete from TransporteTerrestreEntity").executeUpdate();
        em.createQuery("delete from ComboEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ActividadEntity actividades = factory.manufacturePojo(ActividadEntity.class);
            em.persist(actividades);
            actividadesData.add(actividades);
        }
        for (int i = 0; i < 3; i++) {
            VueloEntity vuelos = factory.manufacturePojo(VueloEntity.class);
            em.persist(vuelos);
            vuelosData.add(vuelos);
        }
        for (int i = 0; i < 3; i++) {
            AlojamientoEntity alojamientos = factory.manufacturePojo(AlojamientoEntity.class);
            em.persist(alojamientos);
            alojamientosData.add(alojamientos);
        }
        for (int i = 0; i < 3; i++) {
            TransporteTerrestreEntity transportesTerrestres = factory.manufacturePojo(TransporteTerrestreEntity.class);
            em.persist(transportesTerrestres);
            transportesTerrestresData.add(transportesTerrestres);
        }
        for (int i = 0; i < 3; i++) {
            ComboEntity entity = factory.manufacturePojo(ComboEntity.class);
            entity.setPuntuacion((int)(Math.random()*6));
            entity.setCosto((int)(Math.random()*1000));
            entity.setDias(((int)(Math.random()*1000))+1);
            entity.setHoras((int)(Math.random()*1000));
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                actividadesData.get(i).setCombo(entity);
                vuelosData.get(i).setCombo(entity);
                alojamientosData.get(i).setCombo(entity);
                transportesTerrestresData.get(i).setCombo(entity);
            }
        }
    }

    /**
     * Prueba para crear un Combo.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createComboTest() throws BusinessLogicException {
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        ComboEntity result = comboLogic.createCombo(newEntity);
        Assert.assertNotNull(result);
        ComboEntity entity = em.find(ComboEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    /**
     * Prueba para intentar actualizar un combo con una puntuación mayor a 5.
     */
    @Test(expected = BusinessLogicException.class)
    public void failCreateComboPuntuacionMayorA5Test() throws BusinessLogicException {
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setPuntuacion(6);
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.createCombo(newEntity);
        
    }
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failCreateComboPuntuacionMenorA0Test() throws BusinessLogicException {
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setPuntuacion(-1);
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.createCombo(newEntity);
        
    }
       /**
     * Prueba para intentar actualizar un combo con una puntuación mayor a 5.
     */
    @Test(expected = BusinessLogicException.class)
    public void failCreateComboCostoMenorA0Test() throws BusinessLogicException {
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto(-1);
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.createCombo(newEntity);
        
    }
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failCreateComboHorasMenorA0Test() throws BusinessLogicException {
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras(-1);
        comboLogic.createCombo(newEntity);
        
    }
    
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failCreateComboDiasMenorA1Test() throws BusinessLogicException {
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
         newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(0);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.createCombo(newEntity);
        
    }
    
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failCreateComboNombreVacioTest() throws BusinessLogicException {
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setNombre("");
        newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.createCombo(newEntity);
    }
 /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failCreateComboConNullTest() throws BusinessLogicException {
       
        comboLogic.createCombo(null);
        
    }

    


    /**
     * Prueba para consultar la lista de Combos.
     */
    @Test
    public void getCombosTest() {
        List<ComboEntity> list = comboLogic.getCombos();
        Assert.assertEquals(data.size(), list.size());
        for (ComboEntity entity : list) {
            boolean found = false;
            for (ComboEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Combo.
     */
    @Test
    public void getComboTest() {
        ComboEntity entity = data.get(0);
        ComboEntity resultEntity = comboLogic.getCombo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para actualizar un COmbo.
     */
    @Test
    public void updateComboTest() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity pojoEntity = factory.manufacturePojo(ComboEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setPuntuacion((int)(Math.random()*6));
        pojoEntity.setCosto((int)(Math.random()*1000));
        pojoEntity.setDias(((int)(Math.random()*1000))+1);
        pojoEntity.setHoras((int)(Math.random()*1000));
        comboLogic.updateCombo(pojoEntity.getId(), pojoEntity);
        ComboEntity resp = em.find(ComboEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
    
        /**
     * Prueba para intentar actualizar un combo con una puntuación mayor a 5.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboPuntuacionMayorA5Test() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setId(entity.getId());
        newEntity.setPuntuacion(6);
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.updateCombo(newEntity.getId(), newEntity);
        
    }
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboPuntuacionMenorA0Test() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setId(entity.getId());
        newEntity.setPuntuacion(-1);
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.updateCombo(newEntity.getId(), newEntity);
        
    }
    
       /**
     * Prueba para intentar actualizar un combo con una puntuación mayor a 5.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboCostoMenorA0Test() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setId(entity.getId());
        newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto(-1);
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.updateCombo(newEntity.getId(), newEntity);
        
    }
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboHorasMenorA0Test() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setId(entity.getId());
        newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras(-1);
        comboLogic.updateCombo(newEntity.getId(), newEntity);
        
    }
    
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboDiasMenorA1Test() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setId(entity.getId());
         newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(0);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.updateCombo(newEntity.getId(), newEntity);
        
    }
    
    /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboNombreVacioTest() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setId(entity.getId());
        newEntity.setNombre("");
         newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto((int)(Math.random()*1000));
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.updateCombo(newEntity.getId(), newEntity);
    }
 /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboConNullTest() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        comboLogic.updateCombo(entity.getId(), null); 
    }
     /**
     * Prueba para intentar actualizar un combo con una puntuación menor a 0.
     */
    @Test(expected = BusinessLogicException.class)
    public void failUpdateComboConIdNullTest() throws BusinessLogicException {
        ComboEntity entity = data.get(0);
        ComboEntity newEntity = factory.manufacturePojo(ComboEntity.class);
        newEntity.setPuntuacion((int)(Math.random()*6));
        newEntity.setCosto(((int)(Math.random()*1000))+1);
        newEntity.setDias(((int)(Math.random()*1000))+1);
        newEntity.setHoras((int)(Math.random()*1000));
        comboLogic.updateCombo(null, newEntity);
        
    }
    /**
     * Prueba para eliminar un Combo.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteComboTest() throws BusinessLogicException {
        ComboEntity entity = data.get(1);
        comboLogic.deleteCombo(entity.getId());
        ComboEntity deleted = em.find(ComboEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }


}
