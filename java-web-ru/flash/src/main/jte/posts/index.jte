@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <main>
        <meta charset="utf-8"/>
        <h1>Все посты</h1>

        <div class="mb-3">
            <a href="${NamedRoutes.buildPostPath()}">Создать новый пост</a>
        </div>

        <table class="table table-striped">
            @for(var post : page.getPosts())
                <tr>
                    <td>
                        ${post.getId()}
                    </td>
                    <td>
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                    </td>
                    <td>
                        ${post.getBody()}
                    </td>
                </tr>
            @endfor
        </table>
    </main>
`
)
