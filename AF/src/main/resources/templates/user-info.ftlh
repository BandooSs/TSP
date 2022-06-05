<!DOCTYPE html>
<html>
<head>
    <title>AF</title>
</head>
<body>
<label>${us.username}</label>
<form action="/user" method="post">
    <input type="text" name="username" value="${us.username}">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${us.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${us.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>
</body>
</html>