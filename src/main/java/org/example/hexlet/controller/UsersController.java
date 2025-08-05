package org.example.hexlet.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.util.NamedRoutes;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.sql.SQLException;

public class UsersController {
    public static void index(Context ctx) throws SQLException {
        var users = UserRepository.getEntities();
        var page = new UsersPage(users);
        page.setFlash(ctx.consumeSessionAttribute("registration"));
        ctx.render("users/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Not found"));
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("name").trim();
        var email = ctx.formParam("email").toLowerCase().trim();

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .check(value -> value.length() > 6, "У пароля недостаточная длина")
                    .get();
            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.sessionAttribute("registration", "Вы успешно зарегистрировались");
            ctx.redirect(NamedRoutes.usersPath());
        } catch(ValidationException e) {
            ctx.sessionAttribute("registration", "Что-то пошло не так, попробуйте снова");
            var page = new BuildUserPage(name, email, e.getErrors());
            page.setFlash(ctx.consumeSessionAttribute("registration"));
            ctx.render("users/build.jte", model("page", page));
        }
    }

    public static void edit(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Not found"));
        var page = new UserPage(user);
        ctx.render("users/edit.jte", model("page", page));
    }

    public static void update(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Integer.class).get();

        var name = ctx.formParam("name");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        UserRepository.save(user);
        ctx.redirect(NamedRoutes.usersPath());
    }

}
