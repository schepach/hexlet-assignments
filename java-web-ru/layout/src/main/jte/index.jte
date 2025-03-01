@import exercise.Data
@import exercise.dto.users.UsersPage
@param UsersPage usersPage

@template.layout.page(welcome = @`
    <h3 class="center">WELCOME TO MAIN PAGE!</h3>
    <style>
        .center {
            text-align: center;
        }
    </style>
`,
users = null,
user = null)