<#import "macros/cmn.ftlh" as p>
<@p.page>
<H1 class="text-center">Менеджер видов спорта</H1>
<form method="post">
    <div class="form-group">
        <label>Введите название:</label>
        <input type="text" class="form-control" name="name" value="">
        <small class="form-text text-muted">Чтобы добавить новое событие введите название и нажмите кнопку ниже</small>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>
<p></p>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <#list types as type>
        <tr>
            <td>${type.name}</td>
            <td><a href="/typelist/${type.id}">✏️ Edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@p.page>
