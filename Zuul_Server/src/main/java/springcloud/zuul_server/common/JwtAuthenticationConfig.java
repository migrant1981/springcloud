package springcloud.zuul_server.common;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JwtAuthenticationConfig {
	@Value("${zuul_server.security.jwt.url:/login}")
	private String url;

    @Value("${zuul_server.security.jwt.header:Authorization}")
    private String header;

    @Value("${zuul_server.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${zuul_server.security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${zuul_server.security.jwt.secret}")
    private String secret;

	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSecret() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getExpiration() {
		// TODO Auto-generated method stub
		return 0;
	}
}
