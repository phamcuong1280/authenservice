package com.example.accountservice.config.resolvers;//package com.edu.springjwt.config.resolvers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import java.util.HashMap;
//
//public class FilterSnakeCaseArgumentResolver implements HandlerMethodArgumentResolver {
//
//    private final ObjectMapper objectMapper;
//
//    public FilterSnakeCaseArgumentResolver(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return false;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//        var rs = new HashMap<String, Object>();
//        var params = request.getParameterMap();
//        params.keySet().forEach((String key) -> {
//            var paramValue = params.get(key);
//            if (paramValue != null && paramValue.length != 0) {
//                if (paramValue.length == 1) {
//                    if (StringUtils.isNotBlank(paramValue[0])) {
//                        rs.put(key, paramValue[0]);
//                    }
//                } else {
//                    String[] newParamValue = Arrays.stream(paramValue).filter(StringUtils::isNotBlank).toArray(String[]::new);
//                    rs.put(key, newParamValue);
//                }
//            }
//        });
//        return objectMapper.convertValue(rs, parameter.getParameterType());
//    }
//
//}