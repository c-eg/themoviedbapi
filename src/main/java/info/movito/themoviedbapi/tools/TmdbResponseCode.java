package info.movito.themoviedbapi.tools;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * TMDb-API related response codes. See the
 * <a href="https://developer.themoviedb.org/docs/errors">documentation</a> for more info.
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum TmdbResponseCode {
    SUCCESS(1, 200, "Success."),
    INVALID_SERVICE(2, 501, "Invalid service: this service does not exist."),
    AUTHENTICATION_FAILED(3, 401, "Authentication failed: You do not have permissions to access the service."),
    INVALID_FORMAT(4, 405, "Invalid format: This service doesn't exist in that format."),
    INVALID_PARAMETERS(5, 422, "Invalid parameters: Your request parameters are incorrect."),
    INVALID_PRE_REQUISITE_ID(6, 404, "Invalid id: The pre-requisite id is invalid or not found."),
    INVALID_API_KEY(7, 401, "Invalid API key: You must be granted a valid key."),
    DUPLICATE_ENTRY(8, 403, "Duplicate entry: The data you tried to submit already exists."),
    SERVICE_OFFLINE(9, 503, "Service offline: This service is temporarily offline, try again later."),
    SUSPENDED_API_KEY(10, 401, "Suspended API key: Access to your account has been suspended, contact TMDB."),
    INTERNAL_ERROR(11, 500, "Internal error: Something went wrong, contact TMDB."),
    ITEM_UPDATED(12, 201, "The item/record was updated successfully."),
    ITEM_DELETED(13, 200, "The item/record was deleted successfully."),
    AUTHENTICATION_FAILED_2(14, 401, "Authentication failed."),
    FAILED(15, 500, "Failed."),
    DEVICE_DENIED(16, 401, "Device denied."),
    SESSION_DENIED(17, 401, "Session denied."),
    VALIDATION_FAILED(18, 400, "Validation failed."),
    INVALID_ACCEPT_HEADER(19, 406, "Invalid accept header."),
    INVALID_DATE_RANGE(20, 422, "Invalid date range: Should be a range no longer than 14 days."),
    ENTRY_NOT_FOUND(21, 200, "Entry not found: The item you are trying to edit cannot be found."),
    INVALID_PAGE(22, 400, "Invalid page: Pages start at 1 and max at 500. They are expected to be an integer."),
    INVALID_DATE(23, 400, "Invalid date: Format needs to be YYYY-MM-DD."),
    REQUEST_TIMEOUT(24, 504, "Your request to the backend server timed out. Try again."),
    REQUEST_LIMIT_EXCEEDED(25, 429, "Your request count (#) is over the allowed limit of (40)."),
    USERNAME_AND_PASSWORD_REQUIRED(26, 400, "You must provide a username and password."),
    TOO_MANY_APPEND_TO_RESPONSE_OBJECTS(27, 400, "Too many append to response objects: The maximum number of remote calls is 20."),
    INVALID_TIMEZONE(28, 400, "Invalid timezone: Please consult the documentation for a valid timezone."),
    ACTION_CONFIRMATION_REQUIRED(29, 400, "You must confirm this action: Please provide a confirm=true parameter."),
    INVALID_USERNAME_AND_OR_PASSWORD(30, 401, "Invalid username and/or password: You did not provide a valid login."),
    ACCOUNT_DISABLED(31, 401, "Account disabled: Your account is no longer active. Contact TMDB if this is an error."),
    EMAIL_NOT_VERIFIED(32, 401, "Email not verified: Your email address has not been verified."),
    INVALID_REQUEST_TOKEN(33, 401, "Invalid request token: The request token is either expired or invalid."),
    RESOURCE_NOT_FOUND(34, 404, "The resource you requested could not be found."),
    INVALID_TOKEN(35, 401, "Invalid token."),
    TOKEN_NOT_GRANTED_WRITE_PERMISSION(36, 401, "This token hasn't been granted write permission by the user."),
    REQUESTED_SESSION_NOT_FOUND(37, 404, "The requested session could not be found."),
    PERMISSION_DENIED(38, 401, "You don't have permission to edit this resource."),
    RESOURCE_PRIVATE(39, 401, "This resource is private."),
    NOTHING_TO_UPDATE(40, 200, "Nothing to update."),
    REQUEST_TOKEN_NOT_APPROVED(41, 422, "This request token hasn't been approved by the user."),
    REQUEST_METHOD_NOT_SUPPORTED(42, 405, "This request method is not supported for this resource."),
    COULD_NOT_CONNECT_TO_BACKEND_SERVER(43, 502, "Couldn't connect to the backend server."),
    INVALID_ID(44, 500, "The ID is invalid."),
    USER_SUSPENDED(45, 403, "This user has been suspended."),
    API_UNDERGOING_MAINTENANCE(46, 503, "The API is undergoing maintenance. Try again later."),
    INVALID_INPUT(47, 400, "The input is not valid.");

    private final int tmdbCode;

    private final int httpStatus;

    private final String message;

    /**
     * Returns the {@link TmdbResponseCode} for the given tmdbCode.
     *
     * @param tmdbCode the 'status_code'.
     */
    @JsonCreator
    public static TmdbResponseCode fromCode(int tmdbCode) {
        return Arrays.stream(TmdbResponseCode.values())
            .filter(responseCode -> responseCode.getTmdbCode() == tmdbCode)
            .findFirst()
            .orElse(null);
    }
}
