Key : ${encodedKey} <br>
Url : ${url}  <br><br>

<form action="/login/otpTestResult" method="get">
    code : <input name="user_code" type="text"><br><br>
    <input name="encodedKey" type="hidden" readonly="readonly" value="${encodedKey}"><br><br>
    <input type="submit" value="Submit">
</form>