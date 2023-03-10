package com.example.accountservice.common.googleDto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GoogleUtils {

    @Autowired
    private Environment env;

    public String getToken(final String code) throws IOException {
        try {
            String link = env.getProperty("google.link.get.token");
            String response = Request.Post(link)
                    .bodyForm(Form.form().add("client_id", env.getProperty("google.app.id"))
                            .add("client_secret", env.getProperty("google.app.secret"))
                            .add("redirect_uri", env.getProperty("google.redirect.uri")).add("code", code)
                            .add("grant_type", "authorization_code").build())
                    .execute()
                    .returnContent().asString();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response).get("access_token");
            return node.textValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new ClientProtocolException();
    }

    public GooglePojo getUserInfo(final String accessToken) throws IOException {
        String link = env.getProperty("google.link.get.user_info") + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        log.info("[GoogleUtils][getUserInfo] response: " + response);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response, GooglePojo.class);

    }

    public UserDetails buildUser(GooglePojo googlePojo) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(googlePojo.getEmail(),
                "", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
