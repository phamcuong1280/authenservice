package com.example.accountservice.exception;

import com.example.accountservice.exception.constant.HousingBusinessError;
import org.springframework.http.HttpStatus;


public class HousingErrors {
    public static final HousingBusinessError USER_NOT_FOUND = new HousingBusinessError(400004, "USER_NOT_FOUND", HttpStatus.BAD_REQUEST);
    public static final HousingBusinessError EMAIL_EXIST = new HousingBusinessError(400005, "EMAIL IS EXIST", HttpStatus.BAD_REQUEST);
    ;
    /**
     * 400
     */
    public static final HousingBusinessError INVALID_PARAMETERS = new HousingBusinessError(400004, "Invalid parameters", HttpStatus.BAD_REQUEST);
    public static final HousingBusinessError BAD_GATEWAY = new HousingBusinessError(400004, "BAD_GATEWAY", HttpStatus.BAD_GATEWAY);

    public static final HousingBusinessError MAX_FILE_SIZE = new HousingBusinessError(400, "File size exceeded the maximum size", HttpStatus.BAD_REQUEST);
    /**
     * 401
     */
    //401001
    public static final HousingBusinessError USER_NOT_UNAUTHORIZED = new HousingBusinessError(401, "User is unauthorized", HttpStatus.UNAUTHORIZED);
    /**
     * 403
     */

    public static final HousingBusinessError FORBIDDEN_ERROR = new HousingBusinessError(403, "You don not have any permissions to access this resource", HttpStatus.FORBIDDEN);
    /**
     * 404
     */
    public static final HousingBusinessError NOT_FOUND = new HousingBusinessError(404, "URL NOT FOUND", HttpStatus.NOT_FOUND);
    /**
     * 500
     */

    public static final HousingBusinessError INTERNAL_SERVER_ERROR = new HousingBusinessError(500002, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    /**
     * 400-bad request
     */
    public static final HousingBusinessError PREQUALIFED_LEAD_NOT_ALLOW_UPDATE = new HousingBusinessError(400001, "PreQualifiedLead is not allow to update", HttpStatus.BAD_REQUEST);
    public static final HousingBusinessError POTENTIAL_LEAD_ASSIGNED = new HousingBusinessError(400004, "Potential lead has been assigned", HttpStatus.BAD_REQUEST);
    public static final HousingBusinessError INVALID_ACTION = new HousingBusinessError(400005, "", HttpStatus.BAD_REQUEST);
    public static final HousingBusinessError OPPORTUNITY_IS_DUPLICATED = new HousingBusinessError(400009, "Opportunity is exited", HttpStatus.BAD_REQUEST);

    private HousingErrors() {
    }

}
