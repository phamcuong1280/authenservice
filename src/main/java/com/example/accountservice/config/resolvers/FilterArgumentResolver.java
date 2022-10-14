package com.example.applicationgateway.config.resolvers;//package com.edu.springjwt.config.resolvers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.reactive.BindingContext;
//import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//import vn.onehousing.common.infrastructure.configs.filters.Filter;
//
//import java.util.HashMap;
//
//public class FilterArgumentResolver implements HandlerMethodArgumentResolver {
//    private ObjectMapper objectMapper;
//
//    public FilterArgumentResolver(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public boolean supportsParameter(MethodParameter methodParameter) {
//        return Filter.class.isAssignableFrom(methodParameter.getParameterType());
//    }
//
//    @Override
//    public Mono<Object> resolveArgument(MethodParameter parameter,
//                                        BindingContext bindingContext,
//                                        ServerWebExchange exchange) {
//        var rs = new HashMap<String, Object>();
//        var params = exchange.getRequest().getQueryParams();
//        params.keySet().forEach((String key) -> {
//            var paramValue = params.get(key);
//            if (paramValue != null && !paramValue.isEmpty()) {
//                if (paramValue.size() == 1) {
//                    rs.put(key, paramValue.get(0));
//                } else {
//                    rs.put(key, paramValue);
//                }
//            }
//        });
//        return Mono.just(objectMapper.convertValue(rs, parameter.getParameterType()));
//    }
//}