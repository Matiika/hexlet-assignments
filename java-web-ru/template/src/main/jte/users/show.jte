@import exercise.model.User
@param User page

<html>
<head>
    <meta charset="UTF-8">
    <title>Хекслет</title>
</head>
<body>
<h1>Пользователь</h1>
@if(page.equals(null))
    <p>Пока не добавлено ни одного курса</p>
@else
    <div>
        <h2>${page.getId()} ${page.getEmail()}</h2>
        <p>${page.getFirstName()}</p>
        <p>${page.getLastName()}</p>
    </div>
@endif
</body>
</html>
