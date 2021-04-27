<#import "macros/cmn.ftlh" as p>
<@p.page>
    <H1 class="text-center">Менеджер зарегистрированных пользователей</H1>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">✏️ Edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@p.page>
