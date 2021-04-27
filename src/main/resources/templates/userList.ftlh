<#import "macros/cmn.ftlh" as p>
<@p.page>
<H1 class="text-center">Менеджер зарегистрированных пользователей</H1>
    <div class="row">
        <div class="col-9">
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
        </div>
        <div class="col">
            <div class="list-group">
                <label class="badge badge-pill badge-info">Фильтр по пользователям:</label>
                <a href="/user?<#if filter??>filter=${filter}</#if>" class="list-group-item list-group-item-action<#if !type??> active</#if>">
                    Все
                </a>
                <#list roles as rol>
                    <a href="/user?role=${rol}&<#if filter??>filter=${filter}</#if>" class="list-group-item list-group-item-action<#if role?? && role==rol> active</#if>">${rol}</a>
                </#list>
                <form method="get" class="mt-2">
                    <label>Поиск пользователя по имени:</label>
                    <div class="input-group">
                        <input type="search" class="form-control rounded" placeholder="Поиск" name="filter" value="<#if filter??>${filter}</#if>">
                        <input type="hidden" name="role" value="<#if role??>${role}</#if>">
                        <button type="submit" class="btn btn-outline-primary mr-3">🔎</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@p.page>
