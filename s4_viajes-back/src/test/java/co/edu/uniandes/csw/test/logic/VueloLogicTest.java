/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.test.logic;

import co.edu.uniandes.csw.viajes.ejb.VueloLogic;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.VueloPersistence;
import java.sql.Date;
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
 * Pruebas de logica de Vuelo
 * 
 * @author jf.torresp
 */
@RunWith(Arquillian.class)
public class VueloLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private VueloLogic vueloLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<VueloEntity> data = new ArrayList<VueloEntity>();
    
    private List<ProveedorEntity> proveedorData = new ArrayList();
 
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VueloEntity.class.getPackage())
                .addPackage(VueloLogic.class.getPackage())
                .addPackage(VueloPersistence.class.getPackage())
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
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }
    
        /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProveedorEntity proveedor = factory.manufacturePojo(ProveedorEntity.class);
            em.persist(proveedor);
            proveedorData.add(proveedor);
        }
        for (int i = 0; i < 3; i++) {
            VueloEntity entity = factory.manufacturePojo(VueloEntity.class);
            entity.setProveedor(proveedorData.get(0));

            em.persist(entity);
            data.add(entity);
        }    
    }

    /**
     * Prueba para crear un Vuelo.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createVueloTest() throws BusinessLogicException {
        VueloEntity newEntity = factory.manufacturePojo(VueloEntity.class);       
        
        newEntity.setNumero("AVI1234");
        newEntity.setCosto(120.000);
        newEntity.setPuntaje(4);
        newEntity.setLatO(4.6097100);
        newEntity.setLonO(-74.0817500);
        newEntity.setLatD(39.9075000);
        newEntity.setLonD(116.3972300);
        newEntity.setFechaSalida(new Date(0, 0, 0));
        newEntity.setFechaLlegada(new Date(0, 0, 0));
        
        VueloEntity result = vueloLogic.createVuelo(newEntity);
        Assert.assertNotNull(result);
        VueloEntity entity = em.find(VueloEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto(), 0);
        Assert.assertEquals(newEntity.getPuntaje(), entity.getPuntaje(), 0);
        Assert.assertEquals(newEntity.getLatO(), entity.getLatO(), 0);
        Assert.assertEquals(newEntity.getLatD(), entity.getLatD(), 0);
        Assert.assertEquals(newEntity.getLonO(), entity.getLonO(), 0);
        Assert.assertEquals(newEntity.getLonD(), entity.getLonD(), 0);
        Assert.assertEquals(newEntity.getFechaSalida(), entity.getFechaSalida());
        Assert.assertEquals(newEntity.getFechaLlegada(), entity.getFechaLlegada());
    }
  
    /**
     * Prueba para crear un Vuelo con número inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createVueloTestConNumeronvalido() throws BusinessLogicException {
        VueloEntity newEntity = factory.manufacturePojo(VueloEntity.class);
        newEntity.setProveedor(proveedorData.get(0));
        newEntity.setNumero("");
        vueloLogic.createVuelo(newEntity);
    }
    
    /**
     * Prueba para crear un Vuelo con número inválido2.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class) 
    public void createVueloTestConNumerovalido2() throws BusinessLogicException {
        VueloEntity newEntity = factory.manufacturePojo(VueloEntity.class);
        newEntity.setProveedor(proveedorData.get(0));
        newEntity.setNumero(null);
        vueloLogic.createVuelo(newEntity);
    }
    
    /**
     * Prueba para crear un Vuelo con Numero existente.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class) 
    public void createVueloTestConNumeroExistente() throws BusinessLogicException {
        VueloEntity newEntity = factory.manufacturePojo(VueloEntity.class);
        newEntity.setProveedor(proveedorData.get(0));
        newEntity.setNumero(data.get(0).getNumero());
        vueloLogic.createVuelo(newEntity);
    }

    /**
     * Prueba para crear un Vuelo con un proveedor que no existe.
     *
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createVueloTestConProveedorInexistente() throws BusinessLogicException {
        VueloEntity newEntity = factory.manufacturePojo(VueloEntity.class);
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setId(Long.MIN_VALUE);
        newEntity.setProveedor(proveedorEntity);
        vueloLogic.createVuelo(newEntity);
    }

    /**
     * Prueba para crear un Vuelo con proveedor en null.
     *
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createVueloTestConNullProveedor() throws BusinessLogicException {
        VueloEntity newEntity = factory.manufacturePojo(VueloEntity.class);
        newEntity.setProveedor(null);
        vueloLogic.createVuelo(newEntity);
    }
    
       /**
     * Prueba para consultar la lista de Vuelos.
     */
    @Test
    public void getVuelosTest() {
        List<VueloEntity> list = vueloLogic.getVuelos();
        Assert.assertEquals(data.size(), list.size());
        for (VueloEntity entity : list) {
            boolean found = false;
            for (VueloEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Vuelo.
     */
    @Test
    public void getVueloTest() {
        VueloEntity entity = data.get(0);
        VueloEntity resultEntity = vueloLogic.getVuelo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNumero(), resultEntity.getNumero());
        Assert.assertEquals(entity.getCosto(), resultEntity.getCosto(), 0);
        Assert.assertEquals(entity.getPuntaje(), resultEntity.getPuntaje(), 0);
        Assert.assertEquals(entity.getLatO(), resultEntity.getLatO(), 0);
        Assert.assertEquals(entity.getLatD(), resultEntity.getLatD(), 0);
        Assert.assertEquals(entity.getLonO(), resultEntity.getLonO(), 0);
        Assert.assertEquals(entity.getLonD(), resultEntity.getLonD(), 0);
        Assert.assertEquals(entity.getProveedor(), resultEntity.getProveedor());
    }

    /**
     * Prueba para actualizar un Vuelo.
     *
     * @throws BusinessLogicException
     */

    @Test(expected = BusinessLogicException.class)
    public void updateVueloTest() throws BusinessLogicException {
        VueloEntity entity = data.get(0);
        VueloEntity pojoEntity = factory.manufacturePojo(VueloEntity.class);
        pojoEntity.setId(entity.getId());
       
        vueloLogic.updateVuelo(pojoEntity.getId(), pojoEntity);
        VueloEntity resp = em.find(VueloEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNumero(), resp.getNumero());
        Assert.assertEquals(pojoEntity.getCosto(), resp.getCosto(), 0);
        Assert.assertEquals(pojoEntity.getPuntaje(), resp.getPuntaje(), 0);
        Assert.assertEquals(pojoEntity.getLatO(), resp.getLatO(), 0);
        Assert.assertEquals(pojoEntity.getLatD(), resp.getLatD(), 0);
        Assert.assertEquals(pojoEntity.getLonO(), resp.getLonO(), 0);
        Assert.assertEquals(pojoEntity.getLonD(), resp.getLonD(), 0);
    }
    
    /**
     * Prueba para actualizar un Vuelo con Proveedor inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateVueloConProveedornvalidoTest() throws BusinessLogicException {
        VueloEntity entity = data.get(0);
        VueloEntity pojoEntity = factory.manufacturePojo(VueloEntity.class);
        pojoEntity.setNumero("");
        pojoEntity.setId(entity.getId());
        vueloLogic.updateVuelo(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Vuelo conProveedor inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateVueloConProveedorInvalidoTest2() throws BusinessLogicException {
        VueloEntity entity = data.get(0);
        VueloEntity pojoEntity = factory.manufacturePojo(VueloEntity.class);
        pojoEntity.setNumero(null);
        pojoEntity.setId(entity.getId());
        vueloLogic.updateVuelo(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar un Vuelo.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteVueloTest() throws BusinessLogicException {
        VueloEntity entity = data.get(0);
        vueloLogic.deleteVuelo(entity.getId());
        VueloEntity deleted = em.find(VueloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
