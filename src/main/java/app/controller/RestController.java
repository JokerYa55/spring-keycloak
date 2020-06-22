package app.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author vasil
 */
@Controller
@Slf4j
public class RestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/customers")
    public String customers(Model model, HttpServletRequest request, Principal user) {

        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) user;

        RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        log.info("context = {}", context.getRefreshToken());

        log.info("user = {}", token.getAccount().getKeycloakSecurityContext().getToken().toString());
        return "customers";
    }
}
