<#import "macros/cmn.ftlh" as p>
<@p.page>
<div>
    <form method="post">
        <input type="text" name="name" placeholder="Введите название события" />
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Example textarea</label>
            <textarea class="form-control" name="pl_names" rows="5"></textarea>
        </div>
        <button type="submit">Добавить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>

<#list events as event>
    <div>
        <b>${event.id}</b>
        <i>${event.name}</i>
        <strong>${event.ownerName}</strong>
    </div>
<#else>
There is no events yet
</#list>
</@p.page>