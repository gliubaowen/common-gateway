/**
 * 
 */
package com.ibm.common.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.common.core.ssh.SftpService;

/**
 * @author LiuBaoWen
 *
 */
@Configuration
public class CommonGatewayConfig {

	/** SFTP 登录用户名 */
	@Value("${ssh.username}")
	private String username;
	/** SFTP 登录密码 */
	@Value("${ssh.password}")
	private String password;
	/** SFTP 服务器地址 */
	@Value("${ssh.host}")
	private String host;
	/** SFTP 端口 */
	@Value("${ssh.port}")
	private int port = 22;

	@Bean
	public SftpService test() {
		SftpService sftpService = new SftpService();
		sftpService.setSftpProperties(username, password, host, port);
		return sftpService;
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/java/**").filters(f -> f.stripPrefix(1))
						.uri("http://localhost:80/common-web/service/helloworld").order(1))
				.route(r -> r.path("/baidu").uri("http://www.baidu.com"))
				.route("path_route", r -> r.path("/get").uri("http://httpbin.org"))
				.route("host_route", r -> r.host("*.myhost.org").uri("http://httpbin.org"))
				.route("rewrite_route",
						r -> r.host("*.rewrite.org").filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
								.uri("http://httpbin.org"))
				.route("hystrix_route",
						r -> r.host("*.hystrix.org").filters(f -> f.hystrix(c -> c.setName("slowcmd")))
								.uri("http://httpbin.org"))
				.route("hystrix_fallback_route",
						r -> r.host("*.hystrixfallback.org").filters(
								f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
								.uri("http://httpbin.org"))
				.build();
	}

}
