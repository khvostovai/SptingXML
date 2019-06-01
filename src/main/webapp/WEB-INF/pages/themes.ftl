<#import "parts/loginPart.ftl" as l>
<@l.page "themes">
    <form action="/forum/addTheme">
        <button id="addButton" class="btn btn-primary" type="button" data-toggle="modal" data-pid="0" data-action="/add"
                data-target="#addTheme">For add requirement click hire
        </button>
    </form>
    <#if themes??>
        <p><b>List of themes</b></p>
        <#list themes as theme>
            <!-- list of themes -->
            <hr>
            <div class="theme container btn btn-light" id=${theme.id}>
                <p><b>${theme.title}</b></p>
                last message
                <hr>
                Author: ${theme.author.name} ${theme.author.surname}
                <br>
                Date: date
            </div>
        </#list>
    </#if>

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
                <div class="modal-body">
                    <div class="container">
                        <div class="form-group">
                            <label>Title: </label>
                            <input class="form-control" id="title" type="text"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" type="button" class="btn btn-secondary">Close</button>
                    <button id="actionModalButton" class="btn btn-primary" type="button">Save</button>
                </div>
            </div>
        </div>
    </div>

    <!--script for button save in modal-->
    <script>
        $(document).ready(function () {
            // for add theme button
            $("#actionModalButton").on('click', function () {
                $.ajax({
                    url: "/forum/createTheme",
                    type: "POST",
                    data: "title=" + $("#title").val()
                });
            });

            //open theme page in theme id
            $("div.theme").on('click', function () {
                window.location.href = "/forum/theme?themeId="+$(this).attr("id");
            })
        })
    </script>
</@l.page>