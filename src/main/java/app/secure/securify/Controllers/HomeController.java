package app.secure.securify.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import java.security.Principal;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {

        if (principal != null) {
            model.addAttribute("name", principal.getName());
            System.out.println("@@-->Name:"+ principal.getName());
        }
        return "index";
    }

//    @GetMapping("/")
//    public String index(Model model, @AuthenticationPrincipal OAuth2User principal,@RegisteredOAuth2AuthorizedClient("testclient") OAuth2AuthorizedClient authorizedClient) {
//        if (principal != null) {
//            model.addAttribute("name", principal.getAttribute("name"));
//            String accessToken = authorizedClient.getAccessToken().getTokenValue();
//            System.out.println("@@@@--->Access Token: {}"+accessToken);
//        }
//        return "index";
//    }

    @GetMapping("/securedPage/{name}")
    public String securedPage(Model model, @AuthenticationPrincipal OAuth2User principal, @PathVariable String name) {
        if (principal != null) {
            model.addAttribute("name", name);
        }
        return "secured";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            System.out.println("@@@--->Error on login page redirection ---->"+ error);
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }
}
