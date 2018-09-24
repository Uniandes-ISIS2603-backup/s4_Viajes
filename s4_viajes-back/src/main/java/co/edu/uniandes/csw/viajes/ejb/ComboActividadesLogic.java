/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class ComboActividadesLogic {
     private static final Logger LOGGER = Logger.getLogger(ComboActividadesLogic.class.getName());

    @Inject
    private ActividadPersistence actividadPersistence;

    @Inject
    private ComboPersistence comboPersistence;

    /**
     * Agregar un book a la editorial
     *
     * @param actividadId La actividad a guardar
     * @param comboId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public ActividadEntity addActividad(Long actividadId, Long comboId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la editorial con id = {0}", comboId);
        ComboEntity comboEntity = comboPersistence.find(comboId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadId);
        comboEntity.addActividad(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", comboId);
        return actividadEntity;
    }

//    /**
//     * Retorna todos los books asociados a una editorial
//     *
//     * @param editorialsId El ID de la editorial buscada
//     * @return La lista de libros de la editorial
//     */
//    public List<BookEntity> getBooks(Long editorialsId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la editorial con id = {0}", editorialsId);
//        return comboPersistence.find(editorialsId).getBooks();
//    }
//
//    /**
//     * Retorna un book asociado a una editorial
//     *
//     * @param editorialsId El id de la editorial a buscar.
//     * @param booksId El id del libro a buscar
//     * @return El libro encontrado dentro de la editorial.
//     * @throws BusinessLogicException Si el libro no se encuentra en la
//     * editorial
//     */
//    public BookEntity getBook(Long editorialsId, Long booksId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la editorial con id = " + editorialsId, booksId);
//        List<BookEntity> books = comboPersistence.find(editorialsId).getBooks();
//        BookEntity bookEntity = actividadPersistence.find(booksId);
//        int index = books.indexOf(bookEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la editorial con id = " + editorialsId, booksId);
//        if (index >= 0) {
//            return books.get(index);
//        }
//        throw new BusinessLogicException("El libro no está asociado a la editorial");
//    }
//
//    /**
//     * Remplazar books de una editorial
//     *
//     * @param books Lista de libros que serán los de la editorial.
//     * @param editorialsId El id de la editorial que se quiere actualizar.
//     * @return La lista de libros actualizada.
//     */
//    public List<BookEntity> replaceBooks(Long editorialsId, List<BookEntity> books) {
//        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", editorialsId);
//        EditorialEntity editorialEntity = comboPersistence.find(editorialsId);
//        List<BookEntity> bookList = actividadPersistence.findAll();
//        for (BookEntity book : bookList) {
//            if (books.contains(book)) {
//                book.setEditorial(editorialEntity);
//            } else if (book.getEditorial() != null && book.getEditorial().equals(editorialEntity)) {
//                book.setEditorial(null);
//            }
//        }
//        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", editorialsId);
//        return books;
//    }
}
