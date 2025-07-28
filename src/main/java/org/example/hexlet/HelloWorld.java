package org.example.hexlet;

import io.javalin.http.NotFoundResponse;
import org.apache.commons.text.StringEscapeUtils;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;

import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/users/build", ctx -> ctx.render("users/build.jte"));

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").toLowerCase().trim();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/users", ctx -> {
            var users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        app.get("users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var user = UserRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Пользователь не найден"));
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.get("/courses/build", ctx -> ctx.render("courses/build.jte"));

        app.post("/courses", ctx -> {
           var name = ctx.formParam("name").trim();
           var description = ctx.formParam("description");

           var course = new Course(name, description);
           CourseRepository.save(course);
           ctx.redirect("/courses");
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var course = CourseRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Такого курса еще нет"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var courses = CourseRepository.getEntities();
            var header = "Курсы по программированию";
            var title = ctx.queryParam("title");
            var term = ctx.queryParam("term");
            if (title != null) {
                courses = courses.stream()
                        .filter(c -> c.getName().equals(title))
                        .toList();
            }
            if (term != null) {
                courses = courses.stream()
                        .filter(c -> c.getDescription().contains(term))
                        .toList();
            }
            var page = new CoursesPage(courses, header, title, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070);
    }
}
