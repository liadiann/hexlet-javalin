@template.layout.page(
    content = @`
        <form action="/users" method="post">
            <div>
                <label>
                    Имя
                    <input type="text" name="name">
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" name="email" required>
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
                    <input type="password" name="passwordConfirmation required">
                </label>
            </div>
            <input type="submit" value="Зарегистрировать">
        </form>
    `
)