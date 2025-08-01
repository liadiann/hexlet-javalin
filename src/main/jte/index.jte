@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

<!-- Изменения HTML в шаблоне не требуют перезапуска сервера -->
@template.layout.page(
    content = @`
        <h1 class="text-body-emphasis">Привет, Хекслет!</h1>
        <p>Javalin + jte</p>
        <a href="${NamedRoutes.buildSessionPath()}">Войти в учетную запись</a>
        @if(page.getCurrentUser() != null)
            <p>Добро пожаловать, ${page.getCurrentUser()}</p>
            <p>Чтобы разлогиниться, удалите куку JSESSIONID из браузера</p>
        @endif
    `
)
