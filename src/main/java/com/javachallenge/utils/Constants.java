package com.javachallenge.utils;

public final  class Constants {
    private Constants() throws Exception {
        throw new Exception();
    }
    public static final String ACCREDITATION_URL = "/acreditacion";
    public static final String ACREDITACION_GUARDADA = "Acreditacion guardada";
    public static final String COST_MAP = "costMap";
    public static final String COSTO = "costo";
    public static final String COSTO_POR_DESTINO = "costoPorDestino";
    public static final String ERROR_MESSAGE_ERROR_INTERNO = "Se ha producido un error interno";

    public static final String ERROR_MESSAGE_COSTO_ENTRE_PUNTOS_NO_ENCONTRADO = "Costo entre puntos seleccionados no encontrado";
    public static final String ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO = "Punto de venta no encontrado";
    public static final String ERROR_MESSAGE_ACREDITACION_NO_ENCONTRADA = "Acreditacion no encontrado";
    public static final String ERROR_MESSAGE_PUNTO_DE_VENTA_NO_TIENE_RUTA_ASOCIADA = "El Punto de Venta no tiene rutas asociadas";
    public static final String EL_COSTO_DEBE_SER_ENTERO_POSITIVO ="El costo debe ser entero positivo";
    public static final String FECHA_DE_CREACION = "fechaDeCreacion";
    public static final String ID = "id";
    public static final String ID_A = "IdA";
    public static final String ID_B = "IdB";
    public static final String ID_PARAM = "{id}";
    public static final String IMPORTE = "importe";
    public static final String MENOR_COSTO_URL = "/ruta-menor-costo";
    public static final String NOMBRE = "nombre";
    public static final String OK_MESSAGE_COSTO_REMOVDO = "Costo removido";
    public static final String OK_MESSAGE_PUNTO_DE_VENTA_GUARDADO = "Punto de Venta Creado";
    public static final String OK_MESSAGE_PUNTO_DE_VENTA_ACTUALIZADO = "Punto de Venta Actualizado";
    public static final String OK_MESSAGE_PUNTO_DE_VENTA_ELIMINADO = "Punto de Venta Eliminado";
    public static final String PARAMETRO_EN_FORMATO_NO_VALIDO = "Parametro en formato no valido: ";
    public static final String POINT_OF_SALE_MAP = "pointOfSaleMap";
    public static final String PUNTO_DE_VENTA_ID = "puntoDeVentaId";
    public static final String PUNTO_DE_VENTA_NOMBRE = "puntoDeVentaNombre";
    public static final String PUNTO_DE_VENTA_ORIGEN = "puntoDeVentaOrigen";
    public static final String RUTA= "ruta";
    public static final String RUTA_DIRECTA_URL= "/rutas-directas/{id}";
    public static final int MAX_POINT_OF_SALE = 100;
    public static final int MAX_COST = 1000;

}
