@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page


@template.layout.page(
content = @`
    <main>
        @if(page!=null && page.getError()!=null)
            <p>${page.getError()}</p>
        @endif
        <form action="${NamedRoutes.loginPath()}" method="post" style="">
            <div>
                <label>
                    Имя
                    <input type="text" required name="name"/>
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" required name="password"/>
                </label>
            </div>
            <input type="submit" value="Войти"/>
        </form>
    </main>
`
)