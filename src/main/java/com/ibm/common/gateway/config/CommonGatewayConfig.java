/**
 * 
 */
package com.ibm.common.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.common.core.ftp.FtpClientService;
import com.ibm.common.core.ssh.SftpClientService;

/**
 * 配置类
 * 
 * @author LiuBaoWen
 *
 */
@Configuration
public class CommonGatewayConfig {

	/**
	 * 开启sftp客户端服务
	 * 
	 * @author LiuBaoWen
	 * @return
	 */
	@Bean
	public SftpClientService sftpService() {
		return new SftpClientService();
	}

	/**
	 * 开启ftp客户端服务
	 * 
	 * @author LiuBaoWen
	 * @return
	 */
	@Bean
	public FtpClientService ftpClientService() {
		return new FtpClientService();
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
