package exercise.controller;

import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) throws Exception {
        var firstName = ctx.formParamAsClass("firstName", String.class)
                    .get();

        var lastName = ctx.formParamAsClass("lastName", String.class)
                    .get();

        var email = ctx.formParamAsClass("email", String.class)
                    .get();

        var password = ctx.formParamAsClass("email", String.class)
                    .get();

        var token = Security.generateToken();

        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath((long) UserRepository.getEntities().size()));
    }

    public static void show(Context ctx) throws Exception {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var userById = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));
        var cookieToken = String.valueOf(ctx.cookie("token"));
        var cookieUser = userById.getToken();

        if (cookieToken.equals(cookieUser)) {
            var page = new UserPage(userById);
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
