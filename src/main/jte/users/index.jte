@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    page = page,
    content = @`
        @if(page.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользователя</p>
        @else
             @for(var user : page.getUsers())
                    <h2><a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a></h2>
            @endfor
        @endif
    `
)
