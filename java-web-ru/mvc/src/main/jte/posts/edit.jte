@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page


@template.layout.page(
content = @`
    @if(page.getErrors() != null)
        <div class="mb-3">
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        </div>
    @endif

    <main>
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.updatePostPath(page.getId())}" method="post" style="">
                <div class="mb-3">
                    <label class="form-label">
                        Название
                        <input type="text" class="form-control" name="name" value="${page.getName()}"/>
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">Содержание</label>
                    <textarea class="form-control" rows="20" cols="70" type="text"
                              name="body">${page.getBody()}</textarea>
                </div>
                <input type="submit" value="Обновить"/>
                <br>
                <a href="${NamedRoutes.postPath(page.getId())}">Назад</a>
            </form>
        </div>
    </main>
`
)