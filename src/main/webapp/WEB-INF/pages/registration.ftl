<#import "parts/UnLoginPart.ftl" as p>
<@p.page "registration">
    <form action="/register" method="post">
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="first_name" placeholder="First name"
                   type="text"/>
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="patronymic" placeholder="Patronymic"
                   type="text"/>
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="last_name" placeholder="Last name"
                   type="text"/>
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="company" placeholder="Company" type="text"/>
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="login" placeholder="Login" type="text"/>
        </div>
        <div class="form-group">
            <input class="form-control" name="password" placeholder="Password" type="password"/>
        </div>
        <div class="form-group">
            <input class="form-control" name="confirmation" placeholder="Password again" type="password"/>
        </div>
        <button class="btn btn-primary" type="submit">Register</button>
    </form>
</@p.page>