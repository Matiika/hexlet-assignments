@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все посты</h1>

            <table class="table table-striped">
                @for(var post : page.getPosts())
                    <tr>
                        <td>
                            <a href="${NamedRoutes.postPath(post.getId())}">${post.getId()} ${post.getName()}</a>
                        </td>
                        <td>
                            ${post.getBody()}
                        </td>
                    </tr>
                @endfor
            </table>
            <h1>Page ${page.getPageNumber()}</h1>
            <nav>
                @if(page.getPageNumber() > 1)
                    <a href="/posts?page=${page.getPageNumber() - 1}">Previous</a>
                @else
                    <span>Previous</span>
                @endif
                <a href="/posts?page=${page.getPageNumber() + 1}">Next</a>
            </nav>
        </main>
    </div>
`
)
