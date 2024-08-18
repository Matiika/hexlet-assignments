@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>${page.getPost().getId()} ${page.getPost().getName()}</h1>

            <p>${page.getPost().getBody()}</p>
            <a href="/posts">Все посты</a>
        </main>
    </div>
`
)
