<#import "macros/cmn.ftlh" as p>
<@p.page>
<H1 class="text-center">Спортивные события.<#if type??> ${type.name}</#if></H1>
<p></p>
<div class="row">
    <div class="col-9">
        <#include "macros/eventsTable.ftlh" />
    </div>
    <div class="col">
        <div class="list-group">
            <label class="badge badge-pill badge-info">Фильтр по видам спорта:</label>
            <a href="/?<#if filter??>filter=${filter}</#if>" class="list-group-item list-group-item-action<#if !type??> active</#if>">
                Все
            </a>
            <#list types as typ>
                <a href="/?type_id=${typ.id}&<#if filter??>filter=${filter}</#if>" class="list-group-item list-group-item-action<#if type?? && type.id==typ.id> active</#if>">${typ.name}</a>
            </#list>
<#--            <form method="get" class="form-inline">-->
<#--                <label>Поисковой запрос:</label>-->
<#--                <input type="search" name="filter" class="form-control mr-sm-2" value="<#if filter??>${filter}</#if>">-->
<#--                <input type="hidden" name="type_id" value="<#if type??>${type.id}</#if>">-->
<#--                <button class="btn btn-outline-success" type="submit">🔎</button>-->
<#--            </form>-->
<#--            <p></p>-->
            <form method="get" class="mt-2">
                <label>Поиск события по имени:</label>
                <div class="input-group">
                    <input type="search" class="form-control rounded" placeholder="Поиск" name="filter" value="<#if filter??>${filter}</#if>">
                    <input type="hidden" name="type_id" value="<#if type??>${type.id}</#if>">
                    <button type="submit" class="btn btn-outline-primary mr-3">🔎</button>
                </div>
            </form>
        </div>
    </div>
</div>
</@p.page>