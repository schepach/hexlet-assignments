@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes;

@param PostPage page

@template.layout.page(
content = @`
    <main>
        <div class="mb-3">
            <a href="${NamedRoutes.postsPath()}">Все посты</a>
        </div>

        <div style="text-align: center">
            <main>
                ID: <b>${page.getPost().getId()}</b>
                <br>
                Название: <b>${page.getPost().getName()}</b>
                <br>
                Содержание: <b>${page.getPost().getBody()}</b>
            </main>
        </div>
    </main>
`
)