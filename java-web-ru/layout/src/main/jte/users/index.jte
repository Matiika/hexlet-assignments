@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <a>New User</a>
    @for(var user : page.getUsers())
        <p><a href="/users/${user.getId()}">${user.getLastName()}</a></p>
    @endfor
`
)