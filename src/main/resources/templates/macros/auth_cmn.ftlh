<#macro login path isReg>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Логин:</label>
            <div class="col-sm-4">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>" class="form-control ${(usernameError??)?string('is-invalid', '')}" placeholder="User name" />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Пароль:</label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit"><#if isReg>Register<#else>Войти</#if></button>
        <#if !isReg>
            <div><a href="/registration">Нет аккаунта? Кликай, чтобы зарегистрироваться!</a></div>
        <#else>
            <div><a href="/login">Уже есть аккаунт? Кликай, чтобы войти!</a></div>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="dropdown-item" type="submit">Выйти</button>
    </form>
</#macro>
