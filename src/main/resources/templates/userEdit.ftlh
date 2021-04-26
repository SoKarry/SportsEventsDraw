<#import "macros/cmn.ftlh" as p>
<@p.page>
<form action="/user" method="post">
    <div class="form-group">
        <label>User login: </label>
        <input type="text" class="form-control" name="username" value="${user.username}">
        <#list roles as role>
            <div class="form-check">
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                <label class="form-check-label">${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </div>
        <button type="submit" class="btn btn-primary">Save</button>
</form>
</@p.page>