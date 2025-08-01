@import org.example.hexlet.dto.MainPage
@param MainPage page

<!-- Изменения HTML в шаблоне не требуют перезапуска сервера -->
@template.layout.page(
    content = @`
        <h1 class="text-body-emphasis">Привет, Хекслет!</h1>
        <p>Javalin + jte</p>
        @if(!page.isVisited())
            <p>Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки</p>
        @endif
    `
)
