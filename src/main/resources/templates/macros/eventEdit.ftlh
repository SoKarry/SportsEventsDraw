<div>
    <form method="post">
        <div class="form-group">
            <label>Выберите вид спорта</label>
            <select class="form-control" name="type_name">
                <#if types??>
                    <#list types as type>
                    <option <#if event?? && event.type.id==type.id>selected="selected"</#if>>${type.name}</option>
                    </#list>
                </#if>
            </select>
        </div>
        <div class="form-group">
            <label>Выберите вид жеребьёвки</label>
                <#if games??>
                    <input class="form-control" type="text" placeholder="${event.draw.name}" readonly>
                    <small id="emailHelp" class="form-text text-muted">Нельзя изменить вид жеребьёвки после генерации события.</small>
                <#else>
                    <select class="form-control" name="draw_name">
                    <#if draws??>
                        <#list draws as draw>
                            <option <#if event?? && event.draw.id==draw.id>selected="selected"</#if>>${draw.name}</option>
                        </#list>
                    </#if>
                    </select>
                </#if>
        </div>
        <label>Введите название события</label>
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}" value="<#if event??>${event.name}</#if>" name="name" placeholder="Название события" />
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Введите игроков для жеребьёвки: </label>
            <textarea class="form-control ${(pl_namesError??)?string('is-invalid', '')}" name="pl_names" rows="5" <#if games??>readonly</#if>><#if players??><#list players as player>${player.plname}&#10;</#list><#elseif games??><#list games as game>${game.player1Name}&#10;${game.player2Name}&#10;</#list></#if><#if pl_split??><#list pl_split as pl>${pl}&#10;</#list></#if></textarea>
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