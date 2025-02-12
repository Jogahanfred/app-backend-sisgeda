package pe.mil.fap.utils.constants;

public class MessageConstants {

	// DOCUMENT
	public static final String INFO_MESSAGE_DOCUMENT_EXPIRED = "El documento de referencia ha expirado.";

	// VALIDATION
	public static final String INFO_MESSAGE_DATA_ENTERED_INVALID = "Los datos ingresados son inválidos.";

	// LIST
	public static final String SUCCESS_MESSAGE_DATA_RETURNED = "El listado de datos ha sido devuelto correctamente.";
	public static final String SUCCESS_MESSAGE_PAGE_RETURNED = "El paginado ha sido devuelto correctamente.";
	public static final String INFO_MESSAGE_NO_DATA_FOUND = "No se encontraron datos para mostrar.";
	public static final String INFO_MESSAGE_ITEM_DELETED = "El item ya ha sido eliminado.";
	
	// VISIT
	public static final String INFO_MESSAGE_VISIT_NOT_FOUND = "La visita no ha sido encontrada.";
	public static final String SUCCESS_MESSAGE_VISIT_FOUND = "La visita ha sido encontrada con éxito.";
	public static final String SUCCESS_MESSAGE_VISIT_CREATED = "La visita ha sido creada con éxito.";
	public static final String SUCCESS_MESSAGE_VISITOR_UPDATED_SITUATION = "La situación del visitante ha sido actualizada con éxito.";
	
	// MAINTENANCE VISIT
	public static final String SUCCESS_MESSAGE_VISIT_ENTRANCE = "Se ha registrado el ingreso correctamente.";
	public static final String SUCCESS_MESSAGE_VISIT_EXIT = "Se ha registrado la salida correctamente.";
	public static final String SUCCESS_MESSAGE_VISIT_ENTRANCE_DUPLICATE = "El visitante ya tiene registrado ingreso.";
	public static final String SUCCESS_MESSAGE_VISIT_EXIT_DUPLICATE = "El visitante ya tiene registrado salida.";
	public static final String INFO_MESSAGE_SEGMENT_VISIT_NOT_FOUND = "El segmento de visita no ha sido encontrado.";
	public static final String INFO_MESSAGE_VISIT_RANGE_INVALID = "La visita no esta permitida en este rango de fecha.";
	
	// SQUADRON
	public static final String SUCCESS_MESSAGE_SQUADRON_FOUND = "El escuadrón ha sido encontrado con éxito.";
	public static final String SUCCESS_MESSAGE_SQUADRON_CREATED = "El escuadrón ha sido creado con éxito.";
	public static final String SUCCESS_MESSAGE_SQUADRON_UPDATED = "El escuadrón ha sido actualizado con éxito.";
	public static final String SUCCESS_MESSAGE_SQUADRON_DELETED = "El escuadrón ha sido eliminado con éxito.";
	public static final String INFO_MESSAGE_SQUADRON_NOT_FOUND = "El escuadrón no ha sido encontrado.";

	// VISITOR
	public static final String SUCCESS_MESSAGE_VISITOR_FOUND = "La persona ha sido encontrada con éxito.";
	public static final String SUCCESS_MESSAGE_VISITOR_CREATED = "La persona ha sido creada con éxito.";
	public static final String SUCCESS_MESSAGE_VISITOR_UPDATED = "La persona ha sido actualizada con éxito."; 
	public static final String INFO_MESSAGE_VISITOR_NOT_FOUND = "La persona no ha sido encontrada.";
	public static final String INFO_DUPLICATE_VISITOR_ID = "La persona se encuentra en duplicidad.";
	public static final String INFO_MESSAGE_VISITOR_NOT_PERMITTED = "La persona no esta autorizada a ingresar.";

	public static final String INFO_MESSAGE_VISITOR_DETENTION = "Se detuvo a la persona. No esta permitida.";
	public static final String INFO_MESSAGE_VISIT_NOT_AUTHORIZED_TIME = "La persona no esta autorizada a ingresar en este horario.";
	public static final String INFO_MESSAGE_NO_WORKING_HOURS_FOR_TODAY = "No hay horario laboral para hoy.";

	// DOCUMENT
	public static final String SUCCESS_MESSAGE_DOCUMENT_FOUND = "El documento ha sido encontrado con éxito.";
	public static final String SUCCESS_MESSAGE_DOCUMENT_CREATED = "El documento ha sido creado con éxito.";
	public static final String SUCCESS_MESSAGE_DOCUMENT_UPDATED = "El documento ha sido actualizado con éxito."; 
	public static final String INFO_MESSAGE_DOCUMENT_NOT_FOUND = "El documento no ha sido encontrado.";

	// SCHEDULE
	public static final String SUCCESS_MESSAGE_SCHEDULE_CREATED = "La configuración de trabajo ha sido creada con éxito.";
	public static final String INFO_MESSAGE_SCHEDULE_NOT_FOUND = "La configuración de trabajo no ha sido encontrado.";
	
	// SERVER
	public static final String ERROR_IN_SERVICE_SERVER = "Ha ocurrido un error en el servicio.";
	public static final String ERROR_IN_READING_A_FILE = "Ha ocurrido un error en la manipulación del archivo.";

	// DATE
	public static final String INFO_INVALID_START_DATE = "La fecha de inicio debe ser mayor a la actual.";
	public static final String INFO_DOCUMENT_DATE_EXPIRED = "La fecha del documento ya ha expirado.";
	public static final String INFO_INVALID_START_DATE_WORK = "La fecha de ingreso debe ser mayor a la de término.";
	public static final String INFO_DUPLICATE_DAY = "Los dias de trabajo estan duplicados.";

}
