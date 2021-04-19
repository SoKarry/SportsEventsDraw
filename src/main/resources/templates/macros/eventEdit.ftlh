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
            <textarea class="form-control ${(pl_namesError??)?string('is-invalid', '')}" name="pl_names" rows="5"><#if games??><#list games as game>${game.player1Name}&#10;${game.player2Name}&#10;</#list></#if><#if pl_split??><#list pl_split as pl>${pl}&#10;</#list></#if></textarea>
            <#if pl_namesError??>
                <div class="invalid-feedback">
                    ${pl_namesError}
                </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>