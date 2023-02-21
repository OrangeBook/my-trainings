<html>
<head>
  <title>Tools</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>

  <div id="division">
    <div class="wave"></div>
    <div class="wave"></div>
    <div class="wave"></div>
    <form id="mainForm" action="/isYourBirthday">
      <label class="text" id="label" for="names">Choose your name:</label>
      <select name="name" id="names">
        <option value="" selected disabled hidden>Choose here</option>
        <#list persons as person>
          <option value="${person.getName()}">${person.getName()}</option>
        </#list>
      </select>
      <br>
      <input id="input" type="submit" value="Is today my birthday?">
    </form>

      <label class="threeD">${message!""}</label>
  </div>


</body>
</html>