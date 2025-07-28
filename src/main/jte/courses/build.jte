@template.layout.page(
    content = @`
        <form action="/courses" method="post">
            <div>
                <label>
                    Название курса
                    <input type="text" required name="name">
                </label>
            </div>
            <div>
                <label>
                    Описание
                    <input type="text" name="description" required>
                </label>
            </div>
            <input type="submit" value="Создать курс">
        </form>
    `
)