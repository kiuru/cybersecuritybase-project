# F-secure Cyber Security Base - Project
====

## A2 - Broken Authentication and Session Management

Session fixation is disabled so website keeps the same session value after user has logged in. By default Spring creates a new session key after a successful login.
This is a potential risk where it is possible for a malicious attacker to create a session key before user is logged in. Therefore attacker has already user session.

Fix:
Remove line 32 from SecurityConfiguration.java.

```java
http.sessionManagement().sessionFixation().none();
```

## A3 - Cross-Site Scripting (XSS)

Application doesn't have XSS validation so it's easy to change their password with simple XSS.

Following XSS is pretty visible and effective but works as well:

```html
<html><body onload="document.forms[2].submit()"><form method="post" action="/changepass"><input type="hidden" name="newpass" value="pwn"></form></body></html>
```

Fix:
* Remove lines 28-29 from SecurityConfiguration.java.

```java
http.headers().xssProtection().disable();
http.headers().frameOptions().disable();
```

* Change lines 30-31 th:utext to th:text from login.html.

```java
<span th:utext="${signup.name}"></span>
<span th:utext="${signup.website}"></span>
```

Utext means "unescaped text" which allow to print XSS.

## A7 - Missing Function Level Access Control

Application have incomplete function for change password features and it should check user old password.

Fix:
* Uncomment lines 51 and 54 from SignupController.java. Changepass method should be like that:

```java
if (signup.getPassword().equals(oldpass)) {
  signup.setPassword(newpass);
  signupRepository.save(signup);   
}
```

## A8 - Cross-Site Request Forgery (CSRF)

Change victims password with CSRF vulnerability if they click following link:

```html
<style>.s{border:none;background-color:#fff;}.s:hover{text-decoration: underline;}</style><form action="/changepass" method="post"><input hidden type="text" name="newpass" value="pwn"/><input type="submit" class="s" value="http://my-site"/></form>
```

Fix:
Remove line 27 from SecurityConfiguration.java.

```java
http.csrf().disable();
```
