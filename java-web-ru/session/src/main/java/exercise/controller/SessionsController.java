package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", model("page", page));
    }

    public static void createSession(Context ctx) {
        var name = ctx.formParam("name");
        var password = Security.encrypt(ctx.formParam("password"));

        if (UsersRepository.existsByName(name)) {
            var user = UsersRepository.findByName(name).get();
            var userPassword = user.getPassword();
            if (userPassword.hashCode() == password.hashCode()) {
                ctx.sessionAttribute("currentUser", name);
                ctx.redirect(NamedRoutes.rootPath());
            }
        }
        var page = new LoginPage(name, "Wrong username or password");
        ctx.render("build.jte", model("page", page));
    }

    public static void exit(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
