package com.insert.ogbsm.global.jwt.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {
    private final String header;
    private final String secret;
    private final Long accessExp;
    private final Long refreshExp;
    private final String prefix;

    public JwtProperties(String header, String secret, Long accessExp, Long refreshExp, String prefix) {
        this.header = header;
        this.secret = secret;
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
        this.prefix = prefix;
    }
}
