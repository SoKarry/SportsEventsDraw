<#import "macros/cmn.ftlh" as p>
<#import "macros/auth_cmn.ftlh" as l>
<@p.page>

<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION?? && RequestParameters.error??>
    <div class="alert alert-danger" role="alert">
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
</#if>
<H1 class="text-center">Вход</H1>
<p></p>
<@l.login "/login" false/>
</@p.page>