package com.interseguro.app.examinterseguro.commons;

public class GlobalConstants {

    /* APIS */
    public static final String API_IMAGE = "/v1/api/image";

    /* CODES */
    /* GET */

    public static final Integer COD_IMAGE_OBTAINED = 200;
    public static final Integer COD_IMAGE_DETAIL_OBTAINED = 200;
    /* POST */

    public static final Integer COD_IMAGE_CREATED = 201;
    public static final Integer COD_IMAGE_DETAIL_CREATED = 201;
    /* PUT */

    public static final Integer COD_IMAGE_UPDATED = 202;
    public static final Integer COD_IMAGE_DETAIL_UPDATED = 202;
    /* DELETE */

    public static final Integer COD_IMAGE_DELETED = 203;
    public static final Integer COD_IMAGE_DETAIL_DELETED = 203;

    /* MESSAGES */
    /* GET */
    public static final String MSG_IMAGE_OBTAINED = "Image obtained successfully";
    public static final String MSG_IMAGE_DETAIL_OBTAINED = "Image detail obtained successfully";

    /* POST */
    public static final String MSG_IMAGE_CREATED = "Image created successfully";
    public static final String MSG_IMAGE_CREATED_AND_ROTATE = "Image created successfully and rotated";
    public static final String MSG_IMAGE_DETAIL_CREATED = "Image detail created successfully";

    /* PUT */
    public static final String MSG_IMAGE_UPDATED = "Image updated successfully";
    public static final String MSG_IMAGE_DETAIL_UPDATED = "Image detail updated successfully";

    /* DELETE */
    public static final String MSG_IMAGE_DELETED = "Image deleted successfully";
    public static final String MSG_IMAGE_DETAIL_DELETED = "Image detail deleted successfully";


}
