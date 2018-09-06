
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ymespana
 */
@RunWith (Arquillian.class)
public class TransporteTerrestrePersistenceTest {
    
    @Inject
    private TransporteTerrestrePersistence transportePersistence;
    
    @PersistenceContext
    private EntityManager em; 
    
    @Inject
    UserTransaction utx;
    
    private List<TransporteTerrestreEntity> data = new ArrayList<TransporteTerrestreEntity>();
    
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransporteTerrestreEntity.class.getPackage())
                .addPackage(TransporteTerrestrePersistence.class.getPackage())
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
        em.createQuery("delete from TransporteTerrestreEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TransporteTerrestreEntity entity = factory.manufacturePojo(TransporteTerrestreEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    /**
     * Prueba para crear un Alojamiento.
     */
    @Test
    public void createTransporteTerrestreTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);
        TransporteTerrestreEntity result = transportePersistence.create(newEntity);

        Assert.assertNotNull(result);

        TransporteTerrestreEntity entity = em.find(TransporteTerrestreEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDestino(), entity.getDestino()); 
        Assert.assertEquals(newEntity.getProveedor(), entity.getProveedor());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
        Assert.assertEquals(newEntity.getLatitudDestino(), entity.getLatitudDestino());
        Assert.assertEquals(newEntity.getLatitudOrigen(), entity.getLatitudOrigen());
        Assert.assertEquals(newEntity.getLongitudDestino(), entity.getLongitudDestino());
        Assert.assertEquals(newEntity.getLongitudOrigen(), entity.getLongitudOrigen());
        Assert.assertEquals(newEntity.getNumeroDias(), entity.getNumeroDias());
        Assert.assertEquals(newEntity.getNumeroHoras(), entity.getNumeroHoras());
    }
 
    /**
     * Prueba para consultar la lista de Alojamientos.
     */
    @Test
    public void getTransportesTest() {
        List<TransporteTerrestreEntity> list = transportePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TransporteTerrestreEntity ent : list) {
            boolean found = false;
            for (TransporteTerrestreEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Alojamiento.
     */
    @Test
    public void getTransporteTest() {
        TransporteTerrestreEntity entity = data.get(0);
        TransporteTerrestreEntity newEntity = transportePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getDestino(), entity.getDestino()); 
        Assert.assertEquals(newEntity.getProveedor(), entity.getProveedor());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
        Assert.assertEquals(newEntity.getLatitudDestino(), entity.getLatitudDestino());
        Assert.assertEquals(newEntity.getLatitudOrigen(), entity.getLatitudOrigen());
        Assert.assertEquals(newEntity.getLongitudDestino(), entity.getLongitudDestino());
        Assert.assertEquals(newEntity.getLongitudOrigen(), entity.getLongitudOrigen());
        Assert.assertEquals(newEntity.getNumeroDias(), entity.getNumeroDias());
        Assert.assertEquals(newEntity.getNumeroHoras(), entity.getNumeroHoras());
    }

    /**
     * Prueba para eliminar un Alojamiento.
     */
    @Test
    public void deleteTransporteTest() {
        TransporteTerrestreEntity entity = data.get(0);
        transportePersistence.delete(entity.getId());
        TransporteTerrestreEntity deleted = em.find(TransporteTerrestreEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Alojamiento.
     */
    @Test
    public void updateBookTest() {
        TransporteTerrestreEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransporteTerrestreEntity newEntity = factory.manufacturePojo(TransporteTerrestreEntity.class);

        newEntity.setId(entity.getId());

        transportePersistence.update(newEntity);

        TransporteTerrestreEntity resp = em.find(TransporteTerrestreEntity.class, entity.getId());

         Assert.assertEquals(newEntity.getDestino(), resp.getDestino()); 
        Assert.assertEquals(newEntity.getProveedor(), resp.getProveedor());
        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
        Assert.assertEquals(newEntity.getLatitudDestino(), resp.getLatitudDestino());
        Assert.assertEquals(newEntity.getLatitudOrigen(), resp.getLatitudOrigen());
        Assert.assertEquals(newEntity.getLongitudDestino(), resp.getLongitudDestino());
        Assert.assertEquals(newEntity.getLongitudOrigen(), resp.getLongitudOrigen());
        Assert.assertEquals(newEntity.getNumeroDias(), resp.getNumeroDias());
        Assert.assertEquals(newEntity.getNumeroHoras(), resp.getNumeroHoras());
    }
}
