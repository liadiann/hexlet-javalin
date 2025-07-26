package org.example.hexlet;

import org.apache.commons.text.StringEscapeUtils;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));
        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");
            var escapedUserId = StringEscapeUtils.escapeHtml4(userId);
            var escapedPostId = StringEscapeUtils.escapeHtml4(postId);
            ctx.contentType("text/html");
            ctx.result("User ID: " + escapedUserId + "\n" + "Post ID: " + escapedPostId);
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        var course1 = new Course("Архитектура веба", "Javalin");
        course1.setId(0);
        var course2 = new Course("Основы HTML", "HTML");
        course2.setId(1);
        var needCourses = List.of(course1, course2);

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var course = needCourses.stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElseThrow();
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var courses = needCourses;
            var header = "Курсы по программированию";
            var title = ctx.queryParam("title");
            var term = ctx.queryParam("term");
            if (title != null) {
                courses = needCourses.stream()
                        .filter(c -> c.getName().equals(title))
                        .toList();
            }
            if (term != null) {
                courses = needCourses.stream()
                        .filter(c -> c.getDescription().contains(term))
                        .toList();
            }
            var page = new CoursesPage(courses, header, title, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070);
    }
}
