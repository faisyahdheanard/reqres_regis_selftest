package helper;

public class endpoint {

    public static final String host_reqres = "https://reqres.in/api";
    public static final String USER_REQRES = host_reqres + "/users";
    public static String USER_LIST(int pageNumber) {
        return USER_REQRES + "?page=" + pageNumber;
    }
    public static String USER_SINGLE(int user_id) {
        return USER_REQRES + "/" + user_id;
    }
    public static final String USER_REGISTER = host_reqres + "/register";
}