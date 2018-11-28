/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.test.logic;

import co.edu.uniandes.csw.viajes.ejb.ActividadGuiaLogic;
import co.edu.uniandes.csw.viajes.ejb.ActividadLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
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
 * Pruebas de logica de la relacion Actividad - Guias
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ActividadGuiaLogicTest {
    
//    private PodamFactory factory = new PodamFactoryImpl();
//
//    @Inject
//    private ActividadLogic actividadLogic;
//    @Inject
//    private ActividadGuiaLogic actividadGuiaLogic;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    private UserTransaction utx;
//
//    private List<ActividadEntity> data = new ArrayList<ActividadEntity>();
//
//    private List<GuiaEntity> guiasData = new ArrayList();
//
//    /**
//     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
//     * El jar contiene las clases, el descriptor de la base de datos y el
//     * archivo beans.xml para resolver la inyección de dependencias.
//     */
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(ActividadEntity.class.getPackage())
//                .addPackage(ActividadLogic.class.getPackage())
//                .addPackage(ActividadPersistence.class.getPackage())
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * Configuración inicial de la prueba.
//     */
//    @Before
//    public void configTest() {
//        try {
//            utx.begin();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     */
//    private void clearData() {
//        em.createQuery("delete from GuiaEntity").executeUpdate();
//        em.createQuery("delete from ActividadEntity").executeUpdate();
//    }
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     */
//    private void insertData() {
//        for (int i = 0; i < 3; i++) {
//            GuiaEntity guia = factory.manufacturePojo(GuiaEntity.class);
//            em.persist(guia);
//            guiasData.add(guia);
//        }
//        for (int i = 0; i < 3; i++) {
//            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
//            em.persist(entity);
//            data.add(entity);
//            if (i == 0) {
//                guiasData.get(i).setActividad(entity);
//            }
//        }
//    }
//
//    /**
//     * Prueba para asociar un Guia existente a un Actividad.
//     */
//    @Test
//    public void addGuiasTest() {
//        ActividadEntity entity = data.get(0);
//        GuiaEntity guiaEntity = guiasData.get(1);
//        GuiaEntity response = actividadGuiaLogic.addGuia(guiaEntity.getDocumento(), entity.getIdentificador());
//
//        Assert.assertNotNull(response);
//        Assert.assertEquals(guiaEntity.getDocumento(), response.getDocumento());
//    }
//
//    /**
//     * Prueba para obtener una colección de instancias de Guias asociadas a una
//     * instancia Actividad.
//     */
//    @Test
//    public void getGuiasTest() {
//        List<GuiaEntity> list = actividadGuiaLogic.getGuias(data.get(0).getIdentificador());
//
//        Assert.assertEquals(1, list.size());
//    }
//
//    /**
//     * Prueba para obtener una instancia de Guias asociada a una instancia
//     * Actividad.
//     *
//     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void getGuiaTest() throws BusinessLogicException {
//        ActividadEntity entity = data.get(0);
//        GuiaEntity guiaEntity = guiasData.get(0);
//        GuiaEntity response = actividadGuiaLogic.getGuia(entity.getIdentificador(), guiaEntity.getDocumento());
//
//        Assert.assertEquals(guiaEntity.getDocumento(), response.getId());
//        Assert.assertEquals(guiaEntity.getEdad(), response.getEdad());
//        Assert.assertEquals(guiaEntity.getIdioma(), response.getIdioma());
//        Assert.assertEquals(guiaEntity.getNombre(), response.getNombre());
//        Assert.assertEquals(guiaEntity.getEdad(), response.getEdad());
//    }
//
//    /**
//     * Prueba para obtener una instancia de Guias asociada a una instancia
//     * Actividad que no le pertenece.
//     *
//     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void getGuiaNoAsociadoTest() throws BusinessLogicException {
//        ActividadEntity entity = data.get(0);
//        GuiaEntity guiaEntity = guiasData.get(1);
//        actividadGuiaLogic.getGuia(entity.getIdentificador(), guiaEntity.getDocumento());
//    }
//
//    /**
//     * Prueba para remplazar las instancias de Guias asociadas a una instancia
//     * de Actividad.
//     */
//    @Test
//    public void replaceGuiasTest() {
//        ActividadEntity entity = data.get(0);
//        List<GuiaEntity> list = guiasData.subList(1, 3);
//        actividadGuiaLogic.replaceGuias(entity.getIdentificador(), list);
//
//        entity = actividadLogic.getActividad(entity.getIdentificador());
//        Assert.assertFalse(entity.getGuias().contains(guiasData.get(0)));
//        Assert.assertTrue(entity.getGuias().contains(guiasData.get(1)));
//        Assert.assertTrue(entity.getGuias().contains(guiasData.get(2)));
//    }
//    
}
