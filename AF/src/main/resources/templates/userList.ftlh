<!DOCTYPE HTML>
<html >
<head>
    <title>Привет, Работяга!</title>
</head>
<body>
<table class="table table-secondary">
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Редактировать</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="user/${user.id}">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>