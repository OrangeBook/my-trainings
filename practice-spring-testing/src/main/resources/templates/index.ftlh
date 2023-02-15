<html>
<head>
  <title>Tools</title>
</head>

<form id="mainForm" action="/isYourBirthday">
  <label for="names">Choose your name:</label>
  <select name="name" id="names">
    <option value="" selected disabled hidden>Choose here</option>
    <#list persons as person>
      <option value="${person.getName()}">${person.getName()}</option>
    </#list>
  </select>
  <br>
  <input type="submit" value="Is today my birthday?">
</form>
    <label>${message!""}</label>


</body>
</html>