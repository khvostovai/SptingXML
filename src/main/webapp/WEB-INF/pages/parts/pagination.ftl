<#macro pagination url >

    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Pages</a>
        </li>
        <#list 0..countPages as p>
            <#if p == page>
                <li class="page-item active" aria-current="page">
                    <a class="page-link" href="${url}page=${p}">${p+1}<span class="sr-only">(current)</span></a>
                </li>
            <#else>
                <li class="page-item"><a class="page-link" href="${url}page=${p}">${p+1}</a></li>
            </#if>
        </#list>
    </ul>

</#macro>