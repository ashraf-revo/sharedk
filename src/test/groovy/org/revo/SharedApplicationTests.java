package org.revo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SharedApplication.class)
@WebAppConfiguration
public class SharedApplicationTests {
    @Autowired
    MongoOperations mongoOperations;

    @Test
    public void contextLoadsadf() {
    }

    @Test
    public void contextLoads() throws SessionException, InterruptedException {
//        String url = "http://localhost:8080/";
//        String value = Oauth2Rest(url, "ashraf", "ashraf", "revo", "revo").getAccessToken().getValue();
//        System.out.println(value);

//        RevoWebSocket revoWebSocket = new RevoWebSocket(url+"hello", value, null);
//        revoWebSocket.Connect();
//        if (revoWebSocket.isConnected()) {
//            revoWebSocket.subscribe("/user/topic/greetings", new RevoStompHandler<>(Message.class, m ->
//                    System.out.println(m.getContent())
//            ));
//            revoWebSocket.send("/app/hello", new Message("lovex"));
//            Thread.sleep(20000);
//        }
    }

    private OAuth2RestTemplate Oauth2Rest(String url, String email, String password, String app_id, String app_secret) {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        List<String> scopes = new ArrayList<>(2);
        scopes.add("write");
        scopes.add("read");
        resource.setAccessTokenUri(url + "oauth/token");
        resource.setClientId(app_id);
        resource.setClientSecret(app_secret);
        resource.setGrantType("password");
        resource.setScope(scopes);
        resource.setUsername(email);
        resource.setPassword(password);
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(atr));
    }
}
