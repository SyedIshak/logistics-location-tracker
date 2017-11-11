package com.turvo.model;

/**
 * The Class Response.
 */
public class Response {

    /** The status. */
    private String status;

    /** The message. */
    private String message;

    /**
     * Instantiates a new response.
     *
     * @param status
     *            the status
     * @param message
     *            the message
     */
    public Response(String status, String message) {
	this.status = status;
	this.message = message;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * Sets the status.
     *
     * @param status
     *            the new status
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * Sets the message.
     *
     * @param message
     *            the new message
     */
    public void setMessage(String message) {
	this.message = message;
    }

}
