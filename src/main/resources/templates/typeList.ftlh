<#import "macros/cmn.ftlh" as p>
<@p.page>
<H1 class="text-center">Менеджер видов спорта</H1>
    <div class="alert alert-danger" role="alert">
        <b>Внимание!</b>
        Удаление вида спорта повлечёт за собой удаление всех связанных с ним событий!
    </div>
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
<table class="table text-center">
    <thead>
    <tr>
        <th>Вид</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <tbody>
    <#list types as type>
        <tr>
            <td>${type.name}</td>
            <td><a href="/typelist/${type.id}">✏️ Edit</a></td>
            <td><a href="/typelist/del/${type.id}">🗑</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@p.page>
