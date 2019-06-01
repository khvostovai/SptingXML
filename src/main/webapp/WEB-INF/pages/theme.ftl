<#import "parts/loginPart.ftl" as l>
<@l.page "Theme">
    <p><b>List of messages</b></p>
    <#if messages??>
        <#list messages as message>
            <div class="container">
                Author: ${message.author.login}
                <br>
                ${message.message}
                <br>
                <br> ${message.date}
            </div>
        </#list>
    </#if>

    <!-- field for input new message -->
</@l.page>
