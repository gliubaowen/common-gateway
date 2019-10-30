/**
 * 
 */
package com.ibm.common.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuBaoWen
 *
 */
@RestController
@RequestMapping("service")
public class CommonGatewayController {

	@GetMapping("test")
	public String test() {

		return "test";
	}
}
