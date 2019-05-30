<#import "parts/UnLoginPart.ftl" as p>
<@p.page "registration">
    <form action="/forum/registerProcess" modelAtribute="user" method="post">
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" id="login" path="login" name="login" placeholder="login"
                   type="text"/>
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" id="name" path="name" name="name" placeholder="First name"
                   type="text"/>
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" id="surname" path="surname" name="surname" placeholder="Second name"
                   type="text"/>
        </div>
        <div class="form-group">
            <input class="form-control" id="password" path="passwd" name="passwd" placeholder="Password" type="password"/>
        </div>
        <div class="form-group">
            <input class="form-control" name="confirmation" placeholder="Password again" type="password"/>
        </div>
        <button class="btn btn-primary" type="submit">Register</button>
    </form>
</@p.page>