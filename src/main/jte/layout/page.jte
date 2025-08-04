@import gg.jte.Content
@import org.example.hexlet.dto.BasePage
@import org.example.hexlet.util.NamedRoutes

@param Content content
@param BasePage page = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
        @if(page != null && page.getFlash() != null)
            @if(page.getFlash().equals("Что-то пошло не так, попробуйте снова"))
                <div class="alert alert-danger" role="alert">
                    <p>${page.getFlash()}</p>
                </div>
            @else
                <div class="alert alert-success" role="alert">
                    <p>${page.getFlash()}</p>
                </div>
            @endif
        @endif
        ${content}
        <footer>
            <a href="https://github.com/liadiann">Мой гитхаб</a>
        </footer>
    </body>
</html>