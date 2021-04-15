<#macro login path isReg>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
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
            <label class="col-sm-2 col-form-label">Password:</label>
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
        <button class="btn btn-primary" type="submit"><#if isReg>Register<#else>Sign In</#if></button>
        <#if !isReg>
            <div><a href="/registration">Do not have account? Click to register!</a></div>
        <#else>
            <div><a href="/login">Already have account? Click to login!</a></div>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="dropdown-item" type="submit">Sign Out</button>
    </form>
</#macro>
