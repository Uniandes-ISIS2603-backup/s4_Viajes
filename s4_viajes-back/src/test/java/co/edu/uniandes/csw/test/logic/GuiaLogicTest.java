/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.test.logic;

import co.edu.uniandes.csw.viajes.ejb.GuiaLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.GuiaPersistence;
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
 * Pruebas de logica de Guia
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class GuiaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private GuiaLogic guiaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ActividadEntity> actividadData = new ArrayList<ActividadEntity>();

    private List<GuiaEntity> guiaData = new ArrayList<GuiaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(GuiaLogic.class.getPackage())
                .addPackage(GuiaPersistence.class.getPackage())
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
        em.createQuery("delete from GuiaEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
 
        for (int i = 0; i < 3; i++) {
            ActividadEntity actividad = factory.manufacturePojo(ActividadEntity.class);
            em.persist(actividad);
            actividadData.add(actividad);
        }
        
            for (int i = 0; i < 3; i++) {
            GuiaEntity guia = factory.manufacturePojo(GuiaEntity.class);
            guia.setActividad(actividadData.get(0));
            em.persist(guia);
            guiaData.add(guia);
        }
               
        ActividadEntity actividad = factory.manufacturePojo(ActividadEntity.class);
        em.persist(actividad);
        actividad.getGuias().add(guiaData.get(1));
        guiaData.get(1).setActividad(actividad);
    }

    /**
     * Prueba para crear un Guia
     *
     * @throws co.edu.uniandes.csw.tripBuilder.viajes.BusinessLogicException
     */
    @Test
    public void createGuiaTest() throws BusinessLogicException {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        newEntity.setActividad(actividadData.get(0));
        GuiaEntity result = guiaLogic.createGuia(newEntity);
        Assert.assertNotNull(result);
        GuiaEntity entity = em.find(GuiaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDocumento(), entity.getDocumento());
        Assert.assertEquals(newEntity.getEdad(), entity.getEdad());
        Assert.assertEquals(newEntity.getSueldo(), entity.getSueldo());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para crear un Guia con documento inválido
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createGuiaTestConDocInvalido() throws BusinessLogicException {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        newEntity.setActividad(actividadData.get(0));
        newEntity.setDocumento(0.0);
        guiaLogic.createGuia(newEntity);
    }

    /**
     * Prueba para crear un Guia con documento inválido
     *
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createGuiaTestConDocInvalido2() throws BusinessLogicException {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        newEntity.setActividad(actividadData.get(0));
        newEntity.setDocumento(null);
        guiaLogic.createGuia(newEntity);
    }

    /**
     * Prueba para crear un Book con Documento existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createBookTestConDocumentoExistente() throws BusinessLogicException {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        newEntity.setActividad(actividadData.get(0));
        newEntity.setDocumento(guiaData.get(0).getDocumento());
        guiaLogic.createGuia(newEntity);
    }

    /**
     * Prueba para crear un Book con una editorial que no existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createBookTestConEditorialInexistente() throws BusinessLogicException {
        BookEntity newEntity = factory.manufacturePojo(BookEntity.class);
        EditorialEntity editorialEntity = new EditorialEntity();
        editorialEntity.setId(Long.MIN_VALUE);
        newEntity.setEditorial(editorialEntity);
        bookLogic.createBook(newEntity);
    }

    /**
     * Prueba para crear un Book con editorial en null.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createBookTestConNullEditorial() throws BusinessLogicException {
        BookEntity newEntity = factory.manufacturePojo(BookEntity.class);
        newEntity.setEditorial(null);
        bookLogic.createBook(newEntity);
    }

    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getBooksTest() {
        List<BookEntity> list = bookLogic.getBooks();
        Assert.assertEquals(data.size(), list.size());
        for (BookEntity entity : list) {
            boolean found = false;
            for (BookEntity storedEntity : data) {
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
        BookEntity entity = data.get(0);
        BookEntity resultEntity = bookLogic.getBook(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
        Assert.assertEquals(entity.getIsbn(), resultEntity.getIsbn());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateBookTest() throws BusinessLogicException {
        BookEntity entity = data.get(0);
        BookEntity pojoEntity = factory.manufacturePojo(BookEntity.class);
        pojoEntity.setId(entity.getId());
        bookLogic.updateBook(pojoEntity.getId(), pojoEntity);
        BookEntity resp = em.find(BookEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(pojoEntity.getIsbn(), resp.getIsbn());
        Assert.assertEquals(pojoEntity.getImage(), resp.getImage());
    }

    /**
     * Prueba para actualizar un Book con ISBN inválido.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateBookConISBNInvalidoTest() throws BusinessLogicException {
        BookEntity entity = data.get(0);
        BookEntity pojoEntity = factory.manufacturePojo(BookEntity.class);
        pojoEntity.setIsbn("");
        pojoEntity.setId(entity.getId());
        bookLogic.updateBook(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Book con ISBN inválido.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateBookConISBNInvalidoTest2() throws BusinessLogicException {
        BookEntity entity = data.get(0);
        BookEntity pojoEntity = factory.manufacturePojo(BookEntity.class);
        pojoEntity.setIsbn(null);
        pojoEntity.setId(entity.getId());
        bookLogic.updateBook(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteBookTest() throws BusinessLogicException {
        BookEntity entity = data.get(0);
        bookLogic.deleteBook(entity.getId());
        BookEntity deleted = em.find(BookEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteBookWithAuthorTest() throws BusinessLogicException {
        BookEntity entity = data.get(1);
        bookLogic.deleteBook(entity.getId());
    }
}
