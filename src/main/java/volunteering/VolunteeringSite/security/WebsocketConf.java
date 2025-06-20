package volunteering.VolunteeringSite.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.messaging.access.intercept.AuthorizationChannelInterceptor;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebsocketConf implements WebSocketMessageBrokerConfigurer {

    private final ApplicationContext context;

    public WebsocketConf(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOriginPatterns("**").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    private AuthorizationManager<Message<?>> messageAuthenticationManager(){
        MessageMatcherDelegatingAuthorizationManager.Builder message =
                new MessageMatcherDelegatingAuthorizationManager.Builder();
        message.simpDestMatchers("/topic/user/**").authenticated().anyMessage().permitAll();
        return message.build();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        AuthorizationManager<Message<?>> authorizationManager = messageAuthenticationManager();
        AuthorizationChannelInterceptor interceptor = new AuthorizationChannelInterceptor(authorizationManager);
        AuthorizationEventPublisher publisher = new SpringAuthorizationEventPublisher(context);
        interceptor.setAuthorizationEventPublisher(publisher);
        registration.interceptors(interceptor);
    }
}
