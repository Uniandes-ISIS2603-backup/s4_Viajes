/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.test.logic;



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
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Actividad
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class ActividadLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ActividadLogic actividadLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ActividadEntity> data = new ArrayList<ActividadEntity>();

    private List<GuiaEntity> guiaData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
                .addPackage(ActividadLogic.class.getPackage())
                .addPackage(ActividadPersistence.class.getPackage())
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
        em.createQuery("delete from GuiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            GuiaEntity guia = factory.manufacturePojo(GuiaEntity.class);
            em.persist(guia);
            guiaData.add(guia);
        }
        for (int i = 0; i < 3; i++) {
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
            entity.agregarGuia(guiaData.get(0));

            em.persist(entity);
            data.add(entity);
        }

    }

    /**
     * Prueba para crear una Actividad
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createActividadTest() throws BusinessLogicException {
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        newEntity.setGuias(guiaData);
        ActividadEntity result = actividadLogic.createActividad(newEntity);
        Assert.assertNotNull(result);
        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
        Assert.assertEquals(newEntity.getDuracion(), entity.getDuracion());
    
    }

    /**
     * Prueba para crear un Book con ISBN inválido
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createActividadTestConIdInvalido() throws BusinessLogicException {
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        newEntity.setGuias(guiaData);
        newEntity.setId(0L);
        actividadLogic.createActividad(newEntity);
    }

    /**
     * Prueba para crear un Book con ISBN inválido
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createActividadTestConIdInvalido2() throws BusinessLogicException {
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        newEntity.setGuias(guiaData);
        newEntity.setId(null);
        actividadLogic.createActividad(newEntity);
    }

    /**
     * Prueba para crear un Book con ISBN existente.
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createActividadTestConIdExistente() throws BusinessLogicException {
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        newEntity.setGuias(guiaData);
        newEntity.setId(data.get(0).getIdentificador());
        actividadLogic.createActividad(newEntity);
    }

    /**
     * Prueba para crear un Book con una editorial que no existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createActividadTestConGuiasInexistente() throws BusinessLogicException {
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        newEntity.setGuias(null);
        actividadLogic.createActividad(newEntity);
    }


    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getActividadTest() {
        List<ActividadEntity> list = actividadLogic.getActividades();
        Assert.assertEquals(data.size(), list.size());
        for (ActividadEntity entity : list) {
            boolean found = false;
            for (ActividadEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getBookTest() {
        ActividadEntity entity = data.get(0);
        ActividadEntity resultEntity = actividadLogic.getActividad(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCosto(), resultEntity.getCosto());
        Assert.assertEquals(entity.getDuracion(), resultEntity.getDuracion());
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateActividadTest() throws BusinessLogicException {
        ActividadEntity entity = data.get(0);
        ActividadEntity pojoEntity = factory.manufacturePojo(ActividadEntity.class);
        pojoEntity.setId(entity.getId());
        actividadLogic.modificarActividad(pojoEntity.getId(), pojoEntity);
        ActividadEntity resp = em.find(ActividadEntity.class, entity.getId());
        Assert.assertEquals(entity.getId(), resp.getId());
        //Assert.assertEquals(entity.getCosto(), resp.getCosto());
        //Assert.assertEquals(entity.getDuracion(), resp.getDuracion());
        //Assert.assertEquals(entity.getLatitud(), resp.getLatitud());
        //Assert.assertEquals(entity.getLongitud(), resp.getLongitud());
    }

    /**
     * Prueba para actualizar un Book con ISBN inválido.
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateActividadConIdInvalidoTest() throws BusinessLogicException {
        ActividadEntity entity = data.get(0);
        ActividadEntity pojoEntity = factory.manufacturePojo(ActividadEntity.class);
        pojoEntity.setId(0L);
        
        actividadLogic.modificarActividad(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Book con ISBN inválido.
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateActividadConIdInvalidoTest2() throws BusinessLogicException {
        ActividadEntity entity = data.get(0);
        ActividadEntity pojoEntity = factory.manufacturePojo(ActividadEntity.class);
        pojoEntity.setId(null);
        actividadLogic.modificarActividad(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar un Book.
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteActividadTest() throws BusinessLogicException {
        ActividadEntity entity = data.get(0);
        actividadLogic.deleteActividad(entity.getId());
        ActividadEntity deleted = em.find(ActividadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

  
}
