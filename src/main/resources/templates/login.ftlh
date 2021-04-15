<#import "macros/cmn.ftlh" as p>
<#import "macros/auth_cmn.ftlh" as l>
<@p.page>

<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION?? && RequestParameters.error??>
    <div class="alert alert-danger" role="alert">
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
</#if>
Page which help you to Login
<@l.login "/login" false/>
</@p.page>