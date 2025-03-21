<html lang="en">

<head>
    <title>Add new user</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<header class="d-flex justify-content-center py-3">
    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/" class="nav-link active" aria-current="page">Main</a></li>
        <li class="nav-item"><a href="/users" class="nav-link">Users</a></li>
    </ul>
</header>

<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <form action="/users" method="post" style="">
            <div>
                <label>
                    Имя
                    <input type="text" required name="firstName"/>
                </label>
            </div>
            <div>
                <label>
                    Фамилия
                    <input type="text" required name="lastName"/>
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email"/>
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" required name="password"/>
                </label>
            </div>
            <div>
                <label>
                    Подтверждение пароля
                    <input type="password" required name="passwordConfirmation"/>
                </label>
            </div>
            <input type="submit" value="Регистрация"/>
        </form>
    </main>
</div>
</body>
</html>