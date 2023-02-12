<html>
<head>
  <title>Tools</title>
</head>

<form action="/persons?name=bob">
  <label for="names">Choose a name:</label>
  <select name="names" id="names">
    <#list persons as person>
      <option value="${person.getName()}">${person.getName()}</option>
    </#list>
  </select>
  <br>
  <input type="submit" value="Submit">
</form>


</body>
</html>