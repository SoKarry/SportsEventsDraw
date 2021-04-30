<#import "macros/cmn.ftlh" as p>
<#import "macros/auth_cmn.ftlh" as l>
<@p.page>
    <H1 class="text-center">Регистрация</H1>
    <p></p>
${error_exist!""}
<@l.login "/registration" true/>
</@p.page>