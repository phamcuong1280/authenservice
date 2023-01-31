package com.example.accountservice.common.exception;

import com.example.accountservice.common.exception.constant.BusinessError;
import org.springframework.http.HttpStatus;


public class MyErrors {
    public static final BusinessError USER_NOT_FOUND = new BusinessError(400004, "USER_NOT_FOUND", HttpStatus.BAD_REQUEST);
    public static final BusinessError EMAIL_EXIST = new BusinessError(400005, "EMAIL IS EXIST", HttpStatus.BAD_REQUEST);
    public static final BusinessError VALID_CODE_INCORRECT = new BusinessError(400006, "VALID_CODE_INCORRECT", HttpStatus.BAD_REQUEST);

    /**
     * 400
     */
    public static final BusinessError INVALID_PARAMETERS = new BusinessError(400004, "Invalid parameters", HttpStatus.BAD_REQUEST);
    public static final BusinessError BAD_GATEWAY = new BusinessError(400004, "BAD_GATEWAY", HttpStatus.BAD_GATEWAY);

    public static final BusinessError MAX_FILE_SIZE = new BusinessError(400, "File size exceeded the maximum size", HttpStatus.BAD_REQUEST);
    /**
     * 401
     */
    //401001
    public static final BusinessError USER_NOT_UNAUTHORIZED = new BusinessError(401, "User is unauthorized", HttpStatus.UNAUTHORIZED);
    /**
     * 403
     */

    public static final BusinessError FORBIDDEN_ERROR = new BusinessError(403, "You don not have any permissions to access this resource", HttpStatus.FORBIDDEN);
    /**
     * 404
     */
    public static final BusinessError NOT_FOUND = new BusinessError(404, "URL NOT FOUND", HttpStatus.NOT_FOUND);
    /**
     * 500
     */

    public static final BusinessError INTERNAL_SERVER_ERROR = new BusinessError(500002, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    /**
     * 400-bad request
     */
    public static final BusinessError PREQUALIFED_LEAD_NOT_ALLOW_UPDATE = new BusinessError(400001, "PreQualifiedLead is not allow to update", HttpStatus.BAD_REQUEST);
    public static final BusinessError POTENTIAL_LEAD_ASSIGNED = new BusinessError(400004, "Potential lead has been assigned", HttpStatus.BAD_REQUEST);
    public static final BusinessError INVALID_ACTION = new BusinessError(400005, "", HttpStatus.BAD_REQUEST);
    public static final BusinessError OPPORTUNITY_IS_DUPLICATED = new BusinessError(400009, "Opportunity is exited", HttpStatus.BAD_REQUEST);

    private MyErrors() {
    }

}
