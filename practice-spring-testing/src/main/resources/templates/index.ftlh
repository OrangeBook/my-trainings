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
  <input type="submit" value="Submit" onclick="SetData()">
</form>

    <script>
     function SetData(){
     var select = document.getElementsByName('names')[0].value;
     var parameter = select.options[select.selectedIndex].value
     document.forms[0].action = "/persons?name=" + parameter;
     document.forms[0].submit();
     }
    </script>
</body>
</html>