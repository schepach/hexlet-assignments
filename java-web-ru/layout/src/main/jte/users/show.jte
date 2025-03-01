@import exercise.dto.users.UserPage

@param UserPage page

@template.layout.page(welcome = null,
user = @`
    <div style="text-align: center">
        <main>
            Имя: <b>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</b>
            <br>
            Идентификатор: <b>${page.getUser().getId()}</b>
            <br>
            Email: <b>${page.getUser().getEmail()}</b>
        </main>
    </div>
`,
users = null
)
