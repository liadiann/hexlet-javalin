@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(
    page = page,
    content = @`
        <h1>${page.getHeader()}</h1>
        <form action="/courses" method="get">
            <input type="search" placeholder="Поиск по названию" name="title" value="${page.getTitle()}">
            <input type="submit" value="Search">
        </form>
        <form action="/courses" method="get">
            <input type="search" placeholder="Поиск по описанию" name="term" value="${page.getTerm()}">
            <input type="submit" value="Search">
        </form>
        @if(page.getCourses().isEmpty())
            <p>Пока не добавлено ни одного курса</p>
        @else
             @for(var course : page.getCourses())
                <div>
                    <h2><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h2>
                    <p>${course.getDescription()}</p>
                </div>
            @endfor
        @endif
    `
)
