<#import "parts/UnLoginPart.ftl" as p>
<@p.page "index">
    <div class="container col-3 text-center">
        <form action="/forum/loginProcess" modelAttribute="login" method="post">
            <div class="form-group">
                <input autocomplete="off" autofocus class="form-control" id="login" path="login"name="login" placeholder="Login" type="text"/>
            </div>
            <div class="form-group">
                <input class="form-control" id="password" path="password" name="password" placeholder="Password" type="password"/>
            </div>
            <button class="btn btn-primary" type="submit">Log In</button>
        </form>
    </div>
</@p.page>