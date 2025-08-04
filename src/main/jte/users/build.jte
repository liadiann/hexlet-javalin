@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

@template.layout.page(
    page = page,
    content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif
        <form action="/users" method="post">
            <div>
                <label>
                    Имя
                    <input type="text" name="name" value="${page.getName()}">
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" name="email" required value="${page.getEmail()}">
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" name="password" required>
                </label>
            </div>
            <div>
                <label>
                    Подтверждение пароля
                    <input type="password" name="passwordConfirmation" required">
                </label>
            </div>
            <input type="submit" value="Зарегистрировать">
        </form>
    `
)