@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <h1>All Posts</h1>
    <ul>
    @for(var post : page.getPosts())
        <li>
            <h2>${post.getName()}</h2>
            <p>${post.getBody()}</p>
        </li>
    @endfor
    </ul>
`
)