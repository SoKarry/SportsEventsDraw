<#import "macros/cmn.ftlh" as p>
<@p.page>
<form method="post">
    <div class="form-group">
        <label>Название:</label>
        <input type="text" class="form-control" name="name" value="${type.name}">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </div>
        <button type="submit" class="btn btn-primary">Save</button>
</form>
</@p.page>