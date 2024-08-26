@import exercise.util.NamedRoutes
@import exercise.dto.MainPage

@param MainPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        @if(page.getName() != null)
            Добро пожаловать, ${(String) page.getName()}
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <input type="submit" class="btn btn-primary" value="Выйти" />
            </form>
        @endif

        @if(page.getName() == null)
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @endif
    </div>
`
)
