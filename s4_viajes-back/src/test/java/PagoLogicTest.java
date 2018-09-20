/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import co.edu.uniandes.csw.viajes.ejb.PagoLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.PagoPersistence;
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
public class PagoLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PagoLogic pagoLogic;

    @PersistenceContext
    private EntityManager em;

   @Inject
    private UserTransaction utx;

    private List<PagoEntity> data = new ArrayList<PagoEntity>();

    private List<ComboEntity> comboData = new ArrayList();

    private List<ActividadEntity> actividadData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
          return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
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
        em.createQuery("delete from ComboEntity").executeUpdate();
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
        em.createQuery("delete from VueloEntity").executeUpdate();
        em.createQuery("delete from AlojamientoEntity").executeUpdate();
        em.createQuery("delete from TransporteTerrestreEntity").executeUpdate();
       }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
//        for (int i = 0; i < 3; i++) {
//            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
//            em.persist(entity);
//            actividadData.add(entity);
//        }
        for (int i = 0; i < 3; i++) {
            ComboEntity combo = factory.manufacturePojo(ComboEntity.class);
            combo.setPuntuacion((int)(Math.random()*6));
            combo.setCosto((int)(Math.random()*1000));
            combo.setDias(((int)(Math.random()*1000))+1);
            combo.setHoras((int)(Math.random()*1000));
            try
            {
            combo.addVuelo(new VueloEntity());
            throw new Exception("");
            }
            catch(Exception e)
            {
            em.persist(combo);
            comboData.add(combo);
            }
        }
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            entity.setaPagar(comboData.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }
 /**
     * Prueba para crear un Organization.
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException .
     */
    @Test
    public void createPagoTest() throws BusinessLogicException {

//            Assert.assertTrue(!data.isEmpty());
//            Assert.assertTrue(!comboData.isEmpty());

        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setaPagar(comboData.get(0));
        PagoEntity result = pagoLogic.createPago(newEntity);

        Assert.assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.isPagaConTarjeta(), entity.isPagaConTarjeta());
        if(newEntity.isPagaConTarjeta())
          Assert.assertEquals(newEntity.getTarjeta(), entity.getTarjeta());

        Assert.assertEquals(newEntity.getaPagar(), entity.getaPagar());
    }



    /**
     * Prueba para consultar la lista de Organizations.
     */
    @Test
    public void getPagosTest() {
        List<PagoEntity> list = pagoLogic.getPagos();
        Assert.assertEquals(data.size(), list.size());
        for (PagoEntity entity : list) {
            boolean found = false;
            for (PagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Organization.
     */
    @Test
    public void getPagoTest() {
        PagoEntity entity = data.get(0);
        PagoEntity resultEntity = pagoLogic.getPago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
//        Assert.assertEquals(entity.getName(), resultEntity.getName());
//        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
    }

    /**
     * Prueba para actualizar un Organization.
     */
    @Test
    public void updatePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setaPagar(comboData.get(2));


        pagoLogic.updatePago(pojoEntity.getId(), pojoEntity);

        PagoEntity resp = em.find(PagoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
//        Assert.assertEquals(pojoEntity.getName(), resp.getName());
//        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
    }

    /**
     * Prueba para eliminar un Organization.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deletePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        pagoLogic.deletePago(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

   
   
}
