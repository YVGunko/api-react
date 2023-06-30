package com.yg.apireact.model.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {

    @GetMapping("/roles")
    public String rolesPage() {
        return "roles";
    }
}
