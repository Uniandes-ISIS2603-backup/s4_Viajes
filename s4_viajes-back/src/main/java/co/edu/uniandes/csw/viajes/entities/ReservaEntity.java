/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable {
    
    private int cantidadPersonas;
   
    private boolean pagada;
   
    private Date fechaInicio;
   
    private Date fechaFin;
    
    private double costo;
    
    
    @PodamExclude
    @OneToOne(mappedBy = "reserva", fetch=FetchType.LAZY)    
    private VueloEntity vuelo;
    
    private long idVuelo=-1l;
    
    @PodamExclude
    @OneToOne(mappedBy = "reserva", fetch=FetchType.LAZY)    
    private AlojamientoEntity alojamiento;
    
    private long idAlojamiento=-1l;


    @PodamExclude
    @OneToOne(mappedBy = "reserva", fetch=FetchType.LAZY)    
    private ActividadEntity actividad;
    
    private long idActividad=-1l;

    
    @PodamExclude
    @OneToOne(mappedBy = "reserva", fetch=FetchType.LAZY)    
    private TransporteTerrestreEntity transporteTerrestre;
    
    private long idTransporteTerrestre=-1l;


    @PodamExclude
    @OneToOne
    private PagoEntity pago;
    
    @PodamExclude
    @ManyToOne
    private ComboEntity combo;
   
    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    
    public ReservaEntity(){
    }
    
    public ReservaEntity(ActividadEntity actividad,int cantidadPersonas,Date fechaInicio,Date fechaFin){
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        pagada=false;
        this.actividad=actividad;
        this.cantidadPersonas=cantidadPersonas;
        costo=cantidadPersonas*actividad.getCosto();
    }
    public ReservaEntity(TransporteTerrestreEntity transporteTerrestre,int cantidadPersonas,Date fechaInicio,Date fechaFin){
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        pagada=false;
        this.transporteTerrestre=transporteTerrestre;
        this.cantidadPersonas=cantidadPersonas;
        costo=cantidadPersonas*transporteTerrestre.getCosto();
    }
    public ReservaEntity(AlojamientoEntity alojamiento,int cantidadPersonas,Date fechaInicio,Date fechaFin){
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        pagada=false;
        this.alojamiento=alojamiento;
        this.cantidadPersonas=cantidadPersonas;
        costo=cantidadPersonas*alojamiento.getCosto();
    }
    public ReservaEntity(VueloEntity vuelo,int cantidadPersonas,Date fechaInicio,Date fechaFin){
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        pagada=false;
        this.vuelo=vuelo;
        this.cantidadPersonas=cantidadPersonas;
        costo=cantidadPersonas*vuelo.getCosto();
    }
    
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCosto() {
        
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public VueloEntity getVuelo() {
        return vuelo;
    }

    public void setVuelo(VueloEntity vuel) throws Exception {
         if(actividad!=null||alojamiento!=null||transporteTerrestre!=null)
            throw new Exception("No puede tener mas de un servicio por reserva");
        this.vuelo = vuel;
        if(vuelo!=null)
            costo=vuelo.getCosto()*cantidadPersonas;
    
    }

    public AlojamientoEntity getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(AlojamientoEntity aloj) throws Exception {
        if(actividad!=null||vuelo!=null||transporteTerrestre!=null)
            throw new Exception("No puede tener mas de un servicio por reserva");
        this.alojamiento = aloj;
        if(alojamiento!=null)
            costo=alojamiento.getCosto()*cantidadPersonas;
    
    }

    public ActividadEntity getActividad() {
        return actividad;
    }

    public void setActividad(ActividadEntity act) throws Exception {
        if(transporteTerrestre!=null||vuelo!=null||alojamiento!=null)
            throw new Exception("No puede tener mas de un servicio por reserva");
        this.actividad = act;
        if(actividad!=null)
            costo=actividad.getCosto()*cantidadPersonas;
    }

    public TransporteTerrestreEntity getTransporteTerrestre() {
        return transporteTerrestre;
    }

    public void setTransporteTerrestre(TransporteTerrestreEntity transpTerr) throws Exception {
        if(actividad!=null||vuelo!=null||alojamiento!=null)
            throw new Exception("No puede tener mas de un servicio por reserva");
        this.transporteTerrestre = transpTerr;
        if(transporteTerrestre!=null)
            costo=transporteTerrestre.getCosto()*cantidadPersonas;
    }

    public PagoEntity getPago() {
        return pago;
    }

    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    public long getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(long idVuelo) {
        this.idVuelo = idVuelo;
    }

    public long getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(long idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(long idActividad) {
        this.idActividad = idActividad;
    }

    public long getIdTransporteTerrestre() {
        return idTransporteTerrestre;
    }

    public void setIdTransporteTerrestre(long idTransporteTerrestre) {
        this.idTransporteTerrestre = idTransporteTerrestre;
    }

    
    
    
}
