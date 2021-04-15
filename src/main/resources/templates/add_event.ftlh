<#import "macros/cmn.ftlh" as p>
<@p.page>
<div>
    <form method="post">
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}" value="<#if event??>${event.name}</#if>" name="name" placeholder="Введите название события" />
        <#if nameError??>
            <div class="invalid-feedback">
            ${nameError}
            </div>
        </#if>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Введите игроков для жеребьёвки: </label>
            <textarea class="form-control ${(pl_namesError??)?string('is-invalid', '')}" name="pl_names" rows="5"></textarea>
        <#if pl_namesError??>
            <div class="invalid-feedback">
                ${pl_namesError}
            </div>
        </#if>
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