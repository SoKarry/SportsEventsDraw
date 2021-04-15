<#import "macros/cmn.ftlh" as p>
<@p.page>
<#--редактирование события доступно только для владельца-->
<#if isCurrentUser>
    <#include "macros/eventEdit.ftlh" />
</#if>
<#--games-->
<#list games as game>
    <div>${game.id}</div>
    <div>${game.player1Name}</div>
    <div>${game.player2Name}</div>
</#list>
</@p.page>