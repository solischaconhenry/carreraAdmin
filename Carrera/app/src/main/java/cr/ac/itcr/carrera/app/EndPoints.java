package cr.ac.itcr.carrera.app;


public class EndPoints {

    // localhost url
    // public static final String BASE_URL = "http://192.168.0.101/gcm_chat/v1";

    public static final String BASE_URL = "https://asp-api-ajdurancr.c9users.io";
    public static final String LOGIN = BASE_URL + "/user/login";
    public static final String USER = BASE_URL + "/getProducts/_ID_";
    public static final String CHAT_ROOMS = BASE_URL + "/getProducts/_ID_";
    public static final String CHAT_THREAD = BASE_URL + "/chat_rooms/_ID_";
    public static final String CHAT_ROOM_MESSAGE = BASE_URL + "/newProduct/_ID_";
}
