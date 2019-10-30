/**
 * 
 */
package com.ibm.common.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 计算请求时间过滤器
 * 
 * @author LiuBaoWen
 *
 */
@Component
public class RequestTimeFilter implements GlobalFilter, Ordered {

	private static final Logger log = LoggerFactory.getLogger(RequestTimeFilter.class);

	private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

	/**
	 * filter 执行顺序
	 * 
	 * @author LiuBaoWen
	 * @see org.springframework.core.Ordered#getOrder()
	 * @return
	 */
	@Override
	public int getOrder() {
		return 0;
	}

	/**
	 * 过滤器
	 * 
	 * @author LiuBaoWen
	 * @see org.springframework.cloud.gateway.filter.GlobalFilter#filter(org.springframework.web.server.ServerWebExchange,
	 *      org.springframework.cloud.gateway.filter.GatewayFilterChain)
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
			if (startTime != null) {
				log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime)
						+ "ms");
			}
		}));
	}

}
