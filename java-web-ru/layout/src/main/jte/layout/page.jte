@import gg.jte.Content

@param Content welcome
@param Content user
@param Content users

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
<div class="welcome">
    ${welcome}
</div>

<div class="user">
    ${user}
</div>

<div class="users">
    ${users}
</div>

<footer class="py-3 my-4">
    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="https://github.com/schepach" class="nav-link px-2 text-muted">Github</a></li>
    </ul>
    <p class="text-center text-muted">©2025 Oblomov</p>
</footer>

</body>
</html>