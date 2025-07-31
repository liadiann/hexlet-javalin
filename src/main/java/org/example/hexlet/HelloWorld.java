package org.example.hexlet;

import org.apache.commons.text.StringEscapeUtils;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.util.NamedRoutes;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

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
