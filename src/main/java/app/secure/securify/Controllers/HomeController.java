package app.secure.securify.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
        }
        return "index";
    }

//    @GetMapping("/")
//    public String index(Model model, @AuthenticationPrincipal OAuth2User principal,@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {
//        if (principal != null) {
//            model.addAttribute("name", principal.getAttribute("name"));
//            String accessToken = authorizedClient.getAccessToken().getTokenValue();
//            System.out.println("@@@@--->Access Token: {}"+accessToken);
//        }
//        return "index";
//    }

    @GetMapping("/securedPage")
    public String securedPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
        }
        return "secured";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
