<#import "parts/loginPart.ftl" as l>
<#import "parts/pagination.ftl" as p>
<@l.page "Theme">
    <!-- field for input new message -->

    <div class="container">
        <form action="/forum/createMessage" modelAttribute="newMessage" method="post">
            enter new message:
            <textarea class="form-control" id="message" path="message" name="message"></textarea>
            <button class="btn btn-primary" type="submit">
                post message
            </button>
        </form>
    </div>

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

    <@p.pagination "/forum/theme?themeId=${themeId}&"/>
    ${themeId}

</@l.page>
