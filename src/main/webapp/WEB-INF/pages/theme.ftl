<#import "parts/loginPart.ftl" as l>
<@l.page "Theme">
    <div class="container col-8">
        <!-- field for input new message -->
        <#if page == 0>
            <div class="container">
                <form action="/forum/createMessage" modelAttribute="newMessage" method="post">
                    enter new message:
                    <textarea class="form-control" id="message" path="message" name="message"></textarea>
                    <input type="hidden" id="theme_id" name="theme_id" value="${theme_id}">
                    <button class="btn btn-primary" type="submit">
                        post message
                    </button>
                </form>
            </div>
        </#if>

        <#if messages??>
            <#list messages as message>
                <div class="container">
                    Author: ${message.author.login}
                    <br>
                    ${message.message}
                    <br> ${message.date}
                    <hr>
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
                        <a class="page-link" href="/forum/theme?theme_id=${theme_id}&page=${p}">${p+1}<span
                                    class="sr-only">(current)</span></a>
                    </li>
                <#else>
                    <li class="page-item"><a class="page-link"
                                             href="/forum/theme?theme_id=${theme_id}&page=${p}">${p+1}</a>
                    </li>
                </#if>
            </#list>
        </ul>
    </div>
</@l.page>
