<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../../../../AF/src/main/resources/templates/style2.css" />
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <title>Привет, Работяга!</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Af</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="
        navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 top-menu">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="tournament">Турниры</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/team">Команды</a>
                </li>
            </ul>


            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"><i class="
                    fa-solid fa-user-astronaut"></i></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modal-search">Найти команду по названию</a>
                        </li>
                        <#if us.isAdmin()>
                        <li>
                            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modal-delete">Удалить команду по Id</a>
                        </li>

                        <li>
                            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modal-add">Добавить команду</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modal-info">Информация о юзерах</a>
                        </li>
                        </#if>
                        <li>
                            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modal-exit">Выйти</a>
                        </li>

                    </ul>

                    <!--Modal для поиска команды-->
                    <div  class="modal fade" id="modal-search" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Поиск</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/team" method="get">
                                        Поиск по id:<input type="number" name="id"><br>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                        <input type="submit" value="Найти"/>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!--Modal для удаления-->
                    <div class="modal fade" id="modal-delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel2">Удаление</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/team/delete" method="post">

                                        <div class="row mb-3">
                                            <label for=inputEmail class="col-sm-2 col-form-label-lg">id команды</label>
                                            <div class ="col-sm-10">
                                                <input type = "number" class="form-control" name="id">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            </div>
                                        </div>


                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                        <input type="submit" value="Удалить"/>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!--Modal для добавления-->
                    <div class="modal fade" id="modal-add" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel2">Добавление</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/team/create" method="post" enctype="multipart/form-data">
                                        id команды: <input type="number" name="id"><br><br>
                                        Название команды: <input type="text" name="name"><br><br>
                                        Город команды: <input type="text" name="city"><br><br>
                                        Фотка: <input type="file" name="file"><br><br>
                                        id турнира: <input type="number" name="tournament.id"><br><br>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <input type="submit" value="Добавить команду"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Modal для выхода-->
                    <div class="modal fade" id="modal-exit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel2">Выход</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/logout" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <input type="submit" value="Выйти"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="modal-info" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel2">Перейти</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/user" method="get" enctype="multipart/form-data">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <input type="submit" value="Перейти"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </li>
            </ul>
        </div>
    </div>
</nav>
<main>
    <table class="table table-secondary">
        <thead>
        <tr>
            <th scope="col">Номер Команды</th>
            <th scope="col">Название</th>
            <th scope="col">Город</th>
            <th scope="col">Подробнее</th>
            <#if us.isAdmin()>
            <th scope="col">Редактирование</th>
            </#if>
        </tr>
        </thead>
        <tbody>
        <#list teams as team>
            <tr>
                <td score="row">${team.id}</td>
                <td>${team.name}</td>
                <td>${team.city}</td>
                <td> <a href="/team/${team.id}">Подробнее</a> </td>
                <#if us.isAdmin()>
                <td> <a href="/team/${team.id}/edit">Редактировать</a> </td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
</main>
<#if us.isAdmin()>
    <a>Вы админ</a>
</#if>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>