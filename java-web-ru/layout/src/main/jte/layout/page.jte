@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<header>
    <nav>
        <a href="/">Главная</a>
        <a href="/users">Пользователи</a>
    </nav>
</header>

<p>
    <a href="/users">Users</a>
    <a href="/courses">Courses</a>
</p>
${content}

<footer>
    <p>© 2024 Hexlet Javalin Example</p>
    <p><a href="https://gitlab.com/your-profile">Мой профиль на GitLab</a></p>
</footer>
</body>
</html>