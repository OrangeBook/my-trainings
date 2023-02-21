<html>
<head>
  <title>Tools</title>
  <link rel="stylesheet" href="css/test.css">
</head>

<body>
  <input class="dark-light" type="checkbox" id="dark-light" name="dark-light"/>
  <label for="dark-light"></label>

  <div class="light-back"></div>
  <div class="sec-center">

    <form id="mainForm" action="/isYourBirthday">
      <input class="dropdown" type="checkbox" id="dropdown" name="dropdown"/>
      <label class="for-dropdown" for="dropdown">Dropdown Menu </label>
      <div class="section-dropdown">
        <a href="#">Dropdown Link1 </a>
        <a href="#">Dropdown Link2 </a>
        <a href="#">Dropdown Link3 </a>
      </div>
      <br>

        <label for="names">Choose your name:</label>
        <select class="dropdown" name="name" id="names">
          <div class="section-dropdown">
          <option class="section-dropdown" value="" selected disabled hidden>Choose here</option>
          <#list persons as person>
          <option class="section-dropdown" value="${person.getName()}">${person.getName()}</option>
          </#list>
          </div>
        </select>
        <input type="submit" value="Is today my birthday?"/>

    </form>
    <br>
    <label>${message!""}</label>



  </div>
</body>
</html>