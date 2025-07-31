@import gg.jte.Content
@import org.example.hexlet.util.NamedRoutes

@param Content content

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin</title>
    </head>
    <body>
        <header>
            <p><a href="/">Главная страница</a></p>
            <p><a href="${NamedRoutes.coursesPath()}">Курсы</a></p>
            <p><a href="${NamedRoutes.usersPath()}">Пользователи</a></p>
            <p><a href="${NamedRoutes.buildUserPath()}">Регистрация пользователя</a></p>
            <p><a href="${NamedRoutes.buildCoursePath()}">Создать курс</a></p>
        </header>
        ${content}
        <footer>
            <a href="https://github.com/liadiann">Мой гитхаб</a>
        </footer>
    </body>
</html>