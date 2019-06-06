<#import "parts/loginPart.ftl" as l>
<@l.page "themes">
    <div class="container col-8">
        <form action="/forum/addTheme">
            <button id="addButton" class="btn btn-primary" type="button" data-toggle="modal" data-pid="0"
                    data-action="/add"
                    data-target="#addTheme">add Theme
            </button>
        </form>
        <#if themes??>
            <p><b>List of themes</b></p>
            <#list themes as theme>
                <!-- list of themes -->
                <hr>
                <div class="container">
                    <div class="row">
                        <div class="theme col btn btn-light" id=${theme.id}>
                            <p><b>${theme.title}</b></p>
                            <p>Last message: ${theme.date}</p>
                        </div>
                        <#if user_permission>
                            <div class="col-sm-auto">
                                <a class="btn btn-link" href="forum/deleteTheme?themeId=${theme.id}">delete</a>
                            </div>
                        </#if>
                    </div>
                </div>
            </#list>
        </#if>

        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Pages</a>
            </li>
            <#list 0..countPages as p>
                <#if p == page>
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="/forum/themes?page=${p}">${p+1}<span class="sr-only">(current)</span></a>
                    </li>
                <#else>
                    <li class="page-item"><a class="page-link" href="/forum/themes?page=${p}">${p+1}</a>
                    </li>
                </#if>
            </#list>
        </ul>
    </div>

    <!--modal window for add theme-->
    <div class="modal fade" id="addTheme" tabindex="-1" role="dialog" aria-labelledby="addThemeLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addThemeLabel">Add Theme</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form action="/forum/createTheme" modalAttribute="newTheme" method="post">
                    <div class="modal-body">
                        <div class="container">
                            <div class="form-group">
                                <label>Title: </label>
                                <input class="form-control" id="title" name="title" path="title" type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" type="button" class="btn btn-secondary">Close</button>
                        <button id="actionModalButton" class="btn btn-primary" type="submit">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!--script for button save in modal-->
    <script>
        $(document).ready(function () {

            //open theme page in theme id
            $("div.theme").on('click', function () {
                window.location.href = "/forum/theme?theme_id=" + $(this).attr("id");
            })
        })
    </script>
</@l.page>