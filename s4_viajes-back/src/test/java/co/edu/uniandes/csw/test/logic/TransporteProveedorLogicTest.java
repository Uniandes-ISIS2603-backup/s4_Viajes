/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.test.logic;

import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreProveedorLogic;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
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
 *
 * @author Yeferson Espana
 */
@RunWith(Arquillian.class)
public class TransporteProveedorLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TransporteTerrestreLogic transporteLogic;

    @Inject
    private TransporteTerrestreProveedorLogic transporteProveedorLogic; 
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx; 
    
    private List<ProveedorEntity> data = new ArrayList<>();
    
    private List<TransporteTerrestreEntity> transporteData = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(TransporteTerrestreLogic.class.getPackage())
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
        em.createQuery("delete from TransporteTerrestreEntity").executeUpdate();
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TransporteTerrestreEntity transportes = factory.manufacturePojo(TransporteTerrestreEntity.class);
            em.persist(transportes);
            transporteData.add(transportes);
        }
        for (int i = 0; i < 3; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                transporteData.get(i).setProveedor(entity);
            }
        } 
    }
    
    /**
     * Prueba para remplazar las instancias de transportees asociadas a una instancia
     * de Proveedor.
     * @throws BusinessLogicException
     */
    @Test
    public void replaceProveedorTest() throws BusinessLogicException {
        TransporteTerrestreEntity entity = transporteData.get(0);
        transporteProveedorLogic.replaceProveedor(entity.getId(), data.get(1).getId());
        entity = transporteLogic.getTransporte(entity.getId());
        Assert.assertEquals(entity.getProveedor(), data.get(1));
    }
    
    /**
     * Prueba para desasociar un transporte existente de un proveedor existente
     *
     * @throws BusinessLogicException
     */
    @Test
    public void removeAlojamientosTest() throws BusinessLogicException {
        transporteProveedorLogic.removeProveedor(transporteData.get(0).getId());
        TransporteTerrestreEntity response = transporteLogic.getTransporte(transporteData.get(0).getId());
        Assert.assertNull(response.getProveedor()); 
    }
    
}
