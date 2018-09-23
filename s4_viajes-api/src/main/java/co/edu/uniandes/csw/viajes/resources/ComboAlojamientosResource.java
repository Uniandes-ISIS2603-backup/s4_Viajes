/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;


import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.ejb.ComboAlojamientosLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *  Clase que implementa el recurso "combos/{id}/alojamientos".
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComboAlojamientosResource {
    
//    private static final Logger LOGGER = Logger.getLogger(ComboAlojamientosResource.class.getName());
//
//    @Inject
//    private ComboAlojamientosLogic comboAlojamientosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
//
//    @Inject
//    private AlojamientoLogic alojamientoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
//
//    /**
//     * Guarda un libro dentro de una editorial con la informacion que recibe el
//     * la URL. Se devuelve el libro que se guarda en la editorial.
//     *
//     * @param comboId Identificador de la editorial que se esta
//     * actualizando. Este debe ser una cadena de dígitos.
//     * @param alojamientoId Identificador del libro que se desea guardar. Este debe
//     * ser una cadena de dígitos.
//     * @return JSON {@link BookDTO} - El libro guardado en la editorial.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro.
//     */
//    @POST
//    @Path("{alojamientoId: \\d+}")
//    public AlojamientoDetailDTO addAlojamiento(@PathParam("comboId") Long comboId, @PathParam("alojamientoId") Long alojamientoId) {
//        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: input: editorialsID: {0} , booksId: {1}", new Object[]{comboId, alojamientoId});
//        if (alojamientoLogic.getBook(alojamientoId) == null) {
//            throw new WebApplicationException("El recurso /books/" + alojamientoId + " no existe.", 404);
//        }
//        BookDTO bookDTO = new BookDTO(comboAlojamientosLogic.addBook(alojamientoId, comboId));
//        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", bookDTO.toString());
//        return bookDTO;
//    }
//
//    /**
//     * Busca y devuelve todos los libros que existen en la editorial.
//     *
//     * @param editorialsId Identificador de la editorial que se esta buscando.
//     * Este debe ser una cadena de dígitos.
//     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
//     * editorial. Si no hay ninguno retorna una lista vacía.
//     */
//    @GET
//    public List<BookDetailDTO> getBooks(@PathParam("editorialsId") Long editorialsId) {
//        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: input: {0}", editorialsId);
//        List<BookDetailDTO> listaDetailDTOs = booksListEntity2DTO(comboAlojamientosLogic.getBooks(editorialsId));
//        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDetailDTOs.toString());
//        return listaDetailDTOs;
//    }
//
//    /**
//     * Busca el libro con el id asociado dentro de la editorial con id asociado.
//     *
//     * @param editorialsId Identificador de la editorial que se esta buscando.
//     * Este debe ser una cadena de dígitos.
//     * @param booksId Identificador del libro que se esta buscando. Este debe
//     * ser una cadena de dígitos.
//     * @return JSON {@link BookDetailDTO} - El libro buscado
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro.
//     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro en la
//     * editorial.
//     */
//    @GET
//    @Path("{booksId: \\d+}")
//    public BookDetailDTO getBook(@PathParam("editorialsId") Long editorialsId, @PathParam("booksId") Long booksId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{editorialsId, booksId});
//        if (alojamientoLogic.getBook(booksId) == null) {
//            throw new WebApplicationException("El recurso /editorials/" + editorialsId + "/books/" + booksId + " no existe.", 404);
//        }
//        BookDetailDTO bookDetailDTO = new BookDetailDTO(comboAlojamientosLogic.getBook(editorialsId, booksId));
//        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: output: {0}", bookDetailDTO.toString());
//        return bookDetailDTO;
//    }
//
//    /**
//     * Remplaza las instancias de Book asociadas a una instancia de Editorial
//     *
//     * @param editorialsId Identificador de la editorial que se esta
//     * remplazando. Este debe ser una cadena de dígitos.
//     * @param books JSONArray {@link BookDTO} El arreglo de libros nuevo para la
//     * editorial.
//     * @return JSON {@link BookDTO} - El arreglo de libros guardado en la
//     * editorial.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro.
//     */
//    @PUT
//    public List<BookDetailDTO> replaceBooks(@PathParam("editorialsId") Long editorialsId, List<BookDetailDTO> books) {
//        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: input: editorialsId: {0} , books: {1}", new Object[]{editorialsId, books.toString()});
//        for (BookDetailDTO book : books) {
//            if (alojamientoLogic.getBook(book.getId()) == null) {
//                throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
//            }
//        }
//        List<BookDetailDTO> listaDetailDTOs = booksListEntity2DTO(comboAlojamientosLogic.replaceBooks(editorialsId, booksListDTO2Entity(books)));
//        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: output: {0}", listaDetailDTOs.toString());
//        return listaDetailDTOs;
//    }
//
//    /**
//     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
//     *
//     * @param entityList Lista de BookEntity a convertir.
//     * @return Lista de BookDTO convertida.
//     */
//    private List<BookDetailDTO> booksListEntity2DTO(List<BookEntity> entityList) {
//        List<BookDetailDTO> list = new ArrayList();
//        for (BookEntity entity : entityList) {
//            list.add(new BookDetailDTO(entity));
//        }
//        return list;
//    }
//
//    /**
//     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
//     *
//     * @param dtos Lista de BookDetailDTO a convertir.
//     * @return Lista de BookEntity convertida.
//     */
//    private List<BookEntity> booksListDTO2Entity(List<BookDetailDTO> dtos) {
//        List<BookEntity> list = new ArrayList<>();
//        for (BookDetailDTO dto : dtos) {
//            list.add(dto.toEntity());
//        }
//        return list;
//    }
}
