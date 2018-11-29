/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author estudiante
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.csw.viajes.filters.CORSFilter.class);
        resources.add(co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ActividadGuiaResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ActividadResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.AdministradorResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.AlojamientoResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ComboReservasResourcee.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ComboResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.EntradaResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.GuiaResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.MedallaResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.PagoResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ProveedorActividadResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ProveedorAlojamientosResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ProveedorResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ProveedorTransportesResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ProveedorVueloResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.ReservaResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.TransporteTerrestreResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.UsuarioCombosResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.UsuarioEntradasResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.UsuarioMedallasResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.UsuarioPagosResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.UsuarioResource.class);
        resources.add(co.edu.uniandes.csw.viajes.resources.VueloResource.class);
    }
    
}
