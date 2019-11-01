/**
 * 
 */
package com.ibm.common.gateway.exception;

/**
 * common-gateway异常类
 * 
 * @author LiuBaoWen
 *
 */
public class CommonGatewayException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7306512356000366889L;

	/** 错误编码 */
	private String errorCode;

	/**
	 * @param message
	 */
	public CommonGatewayException(String message) {
		super(message);
	}

	/**
	 * @param errorCode
	 */
	public CommonGatewayException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public CommonGatewayException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public CommonGatewayException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CommonGatewayException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CommonGatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/** get 错误编码 */
	public String getErrorCode() {
		return errorCode;
	}

}
