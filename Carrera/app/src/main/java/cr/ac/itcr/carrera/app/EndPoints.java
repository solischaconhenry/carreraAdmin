package cr.ac.itcr.carrera.app;


public class EndPoints {

    // localhost url
    // public static final String BASE_URL = "http://192.168.0.101/gcm_chat/v1";

    public static final String BASE_URL = "http://172.24.42.84:9000";
    public static final String USERS = BASE_URL + "/api/carrera/usuarios/todos";
    public static final String USER = BASE_URL + "/getProducts/_ID_";
    public static final String CHAT_ROOMS = BASE_URL + "/api/carrera/eventos/todos";
    public static final String EDITAR_EVENTO = BASE_URL + "/api/carrera/eventos/editar/:idEvento";
    public static final String CHAT_ROOM_MESSAGE = BASE_URL + "/api/carrera/eventos/nuevo";
    public static final String ADMINUSERS =  BASE_URL + "/api/carrera/usuarios/admin";
    public static final String BLOQUSERS =  BASE_URL + "/api/carrera/usuarios/bloquear";
}
