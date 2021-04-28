<#include "sec.ftlh">
<table class="table text-center">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Название события</th>
        <th scope="col">Создатель</th>
        <th scope="col">Вид спорта</th>
        <#if isAdmin || draws??>
            <th scope="col">Удалить</th>
        </#if>
    </tr>
    </thead>
    <tbody>
    <#list events as event>
        <tr>
            <th scope="row">${event.id}</th>
            <td><a href="/event/${event.id}">${event.name}</a></td>
            <td>${event.ownerName}</td>
            <td><a href="/?type_id=${event.type.id}">${event.type.name}</a></td>
            <#if isAdmin || draws??>
                <td><a href="/event/del/${event.id}">🗑</a></td>
            </#if>
        </tr>
    <#else>
        <th colspan="4" class="text-center">There is no events yet</th>
    </#list>
    </tbody>
</table>