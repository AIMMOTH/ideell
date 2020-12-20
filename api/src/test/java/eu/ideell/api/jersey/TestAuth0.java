package eu.ideell.api.jersey;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.container.ContainerRequestContext;

import org.apache.http.HttpHeaders;

import com.auth0.jwk.JwkException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import se.cewebab.stockholm.auth0.Auth0;
import se.cewebab.stockholm.util.Either;

public class TestAuth0 implements Auth0 {

  public static String authorizedHeader = "Bearer access-token-from-auth0";
  private final String subject = "auth0-type|unique-auth0-id-same-for-all-tenants";
  private final String email = "info@cewebab.se";

  @Override
  public Optional<DecodedJWT> authorize(final ContainerRequestContext requestContext) throws JwkException {
    if (authorizedHeader.equals(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION))) {
      return Optional.of(createDecodedJwt(authorizedHeader));
    } else {
      return Optional.empty();
    }
  }
  @Override
  public Either<Exception, JsonObject> getUserInfo(final String accessToken) {
    if (authorizedHeader.equals(accessToken)) {
      return Either.right(JsonParser.parseString("{\r\n" +
          "\"sub\":" + subject + ",\r\n" +
          "\"given_name\":\"Carl\",\r\n" +
          "\"family_name\":\"Emmoth\",\r\n" +
          "\"nickname\":\"info\",\r\n" +
          "\"name\":\"Carl Emmoth\",\r\n" +
          "\"picture\":\"https://stockholm-framework-template.appspot.com/assets/logo.png\",\r\n" +
          "\"locale\":\"sv\",\r\n" +
          "\"updated_at\":\"2020-12-06T15:12:57.405Z\",\r\n" +
          "\"email\":" + email + "," +
          "\"email_verified\":true\r\n" +
          "}").getAsJsonObject());
    } else {
      return Either.left(new Exception("Not implemented"));
    }
  }

  private DecodedJWT createDecodedJwt(final String token) {
    return new DecodedJWT() {

      @Override
      public String getType() {
        return null;
      }

      @Override
      public String getKeyId() {
        return null;
      }

      @Override
      public Claim getHeaderClaim(final String name) {
        return null;
      }

      @Override
      public String getContentType() {
        return null;
      }

      @Override
      public String getAlgorithm() {
        return null;
      }

      @Override
      public String getSubject() {
        return subject;
      }

      @Override
      public Date getNotBefore() {
        return null;
      }

      @Override
      public String getIssuer() {
        return null;
      }

      @Override
      public Date getIssuedAt() {
        return new Date();
      }

      @Override
      public String getId() {
        return null;
      }

      @Override
      public Date getExpiresAt() {
        return null;
      }

      @Override
      public Map<String, Claim> getClaims() {
        return null;
      }

      @Override
      public Claim getClaim(final String name) {
        return null;
      }

      @Override
      public List<String> getAudience() {
        return null;
      }

      @Override
      public String getToken() {
        return token;
      }

      @Override
      public String getSignature() {
        return null;
      }

      @Override
      public String getPayload() {
        return null;
      }

      @Override
      public String getHeader() {
        return null;
      }
    };
  }


}
