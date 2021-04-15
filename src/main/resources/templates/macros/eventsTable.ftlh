<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Название события</th>
        <th scope="col">Создатель</th>
    </tr>
    </thead>
    <tbody>
    <#list events as event>
        <tr>
            <th scope="row">${event.id}</th>
            <td><a href="/event/${event.id}">${event.name}</a></td>
            <td>${event.ownerName}</td>
        </tr>
    <#else>
        There is no events yet
    </#list>
    </tbody>
</table>