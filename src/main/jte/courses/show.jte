@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

<head>
    <meta charset="utf-8">
</head>
<main>
    <h1>${page.getCourse().getName()}</h1>
    <p>${page.getCourse().getDescription()}</p>
</main>
