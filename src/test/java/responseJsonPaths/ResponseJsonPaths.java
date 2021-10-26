package responseJsonPaths;

public interface ResponseJsonPaths {
	public static String loginResponse_token = "token";
	public static String loginResponse_errorMsg = "error";
	public static String registerResponse_token = "token";
	public static String registerResponse_id = "id";
    public static String createUserResponse_name = "name";
    public static String createUserResponse_job = "job";
    public static String createUserResponse_id = "id";

    public static String createUserResponseData_id = "data.id";
    public static String createUserResponseData_email = "data.email";
    public static String createUserResponseData_fn = "data.first_name";
    public static String createUserResponseData_ln = "data.last_name";
    public static String createUserResponseData_avatar = "data.avatar";

    public static String createUserResponseSupport_url = "support.url";
    public static String createUserResponseSupport_text = "support.text";

    public static String createUserResponse_updatedAt = "updatedAt";
}
