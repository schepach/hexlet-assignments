@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(welcome = null,
user = null,
users = @`
    <body>
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
    </body>`
)