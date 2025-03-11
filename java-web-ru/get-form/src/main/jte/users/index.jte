@import exercise.dto.users.UsersPage

@param UsersPage page

<!doctype html>
<html lang="en">

<head>
    <title>Hexlet Javalin</title>
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
<form action="/users" method="get">
    <input type="search" name="term" value="${page.getTerm()}"/>
    <input type="submit" value="Search"/>
</form>

<div class="mx-auto p-4 py-md-5">
    <main>
        <h1>Пользователи</h1>
        <table class="table table-striped">
            @for(var user : page.getUsers())
                <tr>
                    <td>
                        ${user.getId()}
                    </td>
                    <td>
                        <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
                    </td>
                </tr>
            @endfor
        </table>
    </main>
</div>

</body>
</html>