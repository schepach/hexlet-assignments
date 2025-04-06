@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <main>
        <meta charset="utf-8"/>
        <h1>Все посты</h1>
        <table class="table table-striped">
            @for(var post : page.getPosts())
                <tr>
                    <td>
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getId()}</a>
                    </td>
                    <td>
                        ${post.getName()}
                    </td>
                    <td>
                        ${post.getBody()}
                    </td>
                </tr>
            @endfor
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link"
                       href="${NamedRoutes.postsPathByPage(page.getPreviousPage())}">&laquo;</a>
                </li>
                <li class="page-item">
                    <a class="page-link"
                       href="${NamedRoutes.postsPathByPage(page.getNextPage())}">&raquo;</a>
                </li>
            </ul>
        </nav>
    </main>
`
)