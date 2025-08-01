package org.example.hexlet;

import org.apache.commons.text.StringEscapeUtils;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.util.NamedRoutes;

import java.time.LocalDateTime;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.before(ctx -> {
            LocalDateTime time = LocalDateTime.now();
            System.out.println("Дата и время поступления запроса: " + time);
        });

        app.get("/", ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var page = new MainPage(visited);
            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.usersPath(), UsersController::index);

        app.get(NamedRoutes.userPath("{id}"), UsersController::show);

        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);

        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);

        app.start(7070);
    }
}
