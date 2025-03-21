@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page


<html lang="en">

<head>
    <title>Add new article</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<header class="d-flex justify-content-center py-3">
    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/" class="nav-link active" aria-current="page">Main</a></li>
        <li class="nav-item"><a href="/articles" class="nav-link">Articles</a></li>
    </ul>
</header>

<body>
<div class="mx-auto p-4 py-md-5">
    <main>

        @if(page.getErrors()!=null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>❗${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="/articles" method="post">
            <div>
                <label>
                    Заголовок
                    <br>
                    @if(page.getTitle()!=null)
                        <input type="text" required name="title" placeholder="Заголовок"
                               value="${page.getTitle()}"/>
                    @else
                        <input type="text" required name="title" placeholder="Заголовок"/>
                    @endif
                </label>
            </div>
            <div>
                <label for="text-area">Содержимое</label>
                <br>
                @if(page.getContent()!=null)
                    <textarea id="text-area" required name="content"
                              rows="5" cols="45"
                              placeholder="Содержимое статьи">${page.getContent()}</textarea>
                @else
                    <textarea id="text-area" required name="content"
                              rows="5" cols="45"
                              placeholder="Содержимое статьи"></textarea>
                @endif
            </div>
            <input type="submit" value="Добавить"/>
        </form>
    </main>
</div>
</body>
</html>