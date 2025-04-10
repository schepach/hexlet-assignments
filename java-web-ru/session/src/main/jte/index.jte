@import exercise.util.NamedRoutes;
@import exercise.dto.MainPage

@param MainPage  page


@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            @if(page==null)
                <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
            @else
                <div>
                    <p>${page.getName().toString()}</p>
                    <form action="${NamedRoutes.logoutPath()}" method="post" style="">
                        <input type="submit" value="Выйти"/>
                    </form>
                </div>
            @endif
        </main>
    </div>
`
)
