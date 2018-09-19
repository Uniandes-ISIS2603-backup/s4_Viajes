/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.test.logic;

import co.edu.uniandes.csw.viajes.ejb.ProveedorLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
 * Pruebas de logica de Proveedor
 * 
 * @author jf.torresp
 */
@RunWith(Arquillian.class)
public class ProveedorLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProveedorLogic proveedorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ProveedorEntity> data = new ArrayList<ProveedorEntity>();

    private List<VueloEntity> vuelosData = new ArrayList();
    
    private List<TransporteTerrestreEntity> transportesData = new ArrayList();
    
    private List<ActividadEntity> actividadesData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorLogic.class.getPackage())
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
        em.createQuery("delete from VueloEntity").executeUpdate();
        em.createQuery("delete from TransporteTerrestreEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            VueloEntity vuelos = factory.manufacturePojo(VueloEntity.class);
            em.persist(vuelos);
            vuelosData.add(vuelos);
        }
        for (int i = 0; i < 3; i++) {
           TransporteTerrestreEntity transportes = factory.manufacturePojo(TransporteTerrestreEntity.class); 
           em.persist(transportes);
            transportesData.add(transportes);
        }
        for (int i = 0; i < 3; i++) {
            ActividadEntity actividades = factory.manufacturePojo(ActividadEntity.class);
            em.persist(actividades);
            actividadesData.add(actividades);
        }
        for (int i = 0; i < 3; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);  
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Proveedor
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createProveedorTest() throws BusinessLogicException {
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);

        newEntity.setNombre("Proveedor sas");
        newEntity.setUser("proveedor99");
        newEntity.setPassword("Proveedor123");
        newEntity.setPuntaje(4);
        ProveedorEntity result = proveedorLogic.createProveedor(newEntity);
        
        Assert.assertNotNull(result);
        ProveedorEntity entity = em.find(ProveedorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getUser(), entity.getUser());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
        Assert.assertEquals(newEntity.getPuntaje(), entity.getPuntaje());
    }

    /**
     * Prueba para crear un Proveedor con el mismo nombre de un Proveedor que ya
     * existe.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createProveedorConMismoNombreTest() throws BusinessLogicException {
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        proveedorLogic.createProveedor(newEntity);
    }

    /**
     * Prueba para consultar la lista de Proveedores.
     */
    @Test
    public void getProveedoresTest() {
        List<ProveedorEntity> list = proveedorLogic.getProveedores();
        Assert.assertEquals(data.size(), list.size());
        for (ProveedorEntity entity : list) {
            boolean found = false;
            for (ProveedorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Proveedor.
     */
    @Test
    public void getProveedorTest() {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity resultEntity = proveedorLogic.getProveedor(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getPassword(), resultEntity.getPassword());
        Assert.assertEquals(entity.getUser(), resultEntity.getUser());
        Assert.assertEquals(entity.getPuntaje(), resultEntity.getPuntaje());
    }

    /**
     * Prueba para actualizar un Proveedor.
     */
    @Test
    public void updateProveedorTest() throws BusinessLogicException{
        ProveedorEntity entity = data.get(0);
        ProveedorEntity pojoEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setId(entity.getId());
        proveedorLogic.updateProveedor(pojoEntity.getId(), pojoEntity);
        ProveedorEntity resp = em.find(ProveedorEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getUser(), resp.getUser());
        Assert.assertEquals(pojoEntity.getPassword(), resp.getPassword());
        Assert.assertEquals(pojoEntity.getPuntaje(), resp.getPuntaje());       
    }

    /**
     * Prueba para eliminar un Proveedor.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteProveedorTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(1);
        proveedorLogic.deleteProveedor(entity.getId());
        ProveedorEntity deleted = em.find(ProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminar un Proveedor con vuelos asociados.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteProveedorConInfoAsociadosTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(0);
        proveedorLogic.deleteProveedor(entity.getId());
    }

}
