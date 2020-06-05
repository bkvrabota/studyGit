
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>$First Task for develogic$</title>
  </head>
  <body>

  <%
    Integer viewCount = (Integer)application.getAttribute("hitCounter");
    if( viewCount ==null || viewCount == 0 ) {
      viewCount = 1;
    } else {
      viewCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
  %>

  <p>Page views: <%= viewCount%></p>

  <p>Today <%= new java.util.Date() %></p>
  </body>
</html>
