@import exercise.dto.users.UserPage;
@param UserPage userPage

<html lang="en">
<head>
    <title>Хекслет</title>
</head>
<body>
<div>
    <h2>${userPage.getUser().getId()}">
        ${userPage.getUser().getFirstName() + " " + userPage.getUser().getLastName()}</h2>
</div>
</body>
</html>