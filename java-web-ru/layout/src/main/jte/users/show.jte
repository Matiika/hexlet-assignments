@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <a>${page.getUser().getEmail()}</a>
        <div>${page.getUser().getLastName()}</div>
        <div>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</div>
`
)
