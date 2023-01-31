package com.example.accountservice.common.googleDto;

import com.example.accountservice.infrastructure.models.TypeAccount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePojo {
    private String uuid = UUID.randomUUID().toString();
    private String email;
    private boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String link;
    private String picture;
    private String locale;
    private String type = TypeAccount.google;

    public String toString() {
        return

                "GooglePojo [email=" + this.email + ", verified_email=" + this.verified_email + ", name="
                        + this.name + ", given_name=" + this.given_name + ", family_name=" + this.family_name + "]";
    }
}
