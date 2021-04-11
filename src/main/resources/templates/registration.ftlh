<#import "macros/cmn.ftlh" as p>
<#import "macros/auth_cmn.ftlh" as l>
<@p.page>
Page which help you to Register
${error_exist!""}
<@l.login "/registration" true/>
</@p.page>