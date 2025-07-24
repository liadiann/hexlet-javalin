@import gg.jte.Content

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
            <h2>Курсы</h2>
            <p><a href="/">Главная страница</a></p>
        </header>
        ${content}
        <footer>
            <a href="https://github.com/liadiann">Мой гитхаб</a>
        </footer>
    </body>
</html>