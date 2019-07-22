# SpringWebReact
Example Springmvc+security+hsql+reactjs
1. Configured spring mvc+security project’s
2. create a new react app with no build configuration npm install -g create-react-app
3. Configure "proxy": "http://localhost:8080",
  "homepage": "/SpringWebReact/demo/build" and 
  "build": "react-scripts build && rm -rf ../../src/main/webapp/demo/build/ && mv build/ ../../src/main/webapp/demo/build",
4. Run npm run build on folder react app
5. Run build Springmvc+security+reactjs with using code react
6. Deploy

To create a new react app with no build configuration, create-react-app is the best choice!
npm install -g create-react-app
Let’s assume our spring mvc+security project’s structure as follow and configured spring mvc+security project’s is successfuly
/SpringWebReact
  /src
    /main
      /java
        /com.heb.web.controller
          MainController.java
        /com.heb.web.dao
        /com.heb.web.model
        /com.heb.web.utils
        /com.heb.web.services
      /resources
      /webapp
        /WEB-INF
          /pages
          /layout
            index.jsp
From the root directory of the project we create a new apps folder and inside of this folder we run: create-react-app [name_of_the_app]. We use demo as name for the react app.
In our new react project we need to change the package.json.
proxy: in development mode react app will run on webpack-dev-server on a different port. To enable server calls from the client we need to specify the proxy with the server address.
“proxy”: “localhost:8080”
build: move the build folder to make it available as resource in the Spring project
"build": "react-scripts build && rm -rf ../../src/main/webapp/demo/build/ && mv build/ ../../src/main/webapp/demo/build",
homepage: specify the path of the build with the static files.
"homepage": "/SpringWebReact/demo/build"
The project structure will now look
/root
  /apps
    /demo
      package.json
      /src 
  /src
    /main
      /java
        /com.heb.web.controller
          MainController.java
      /resources
        /demo (react build will go here!)
      /webapp
        /WEB-INF
          /layout
            index.jsp
In Spring project we have to make sure that DispatcherServlet is present in web.xml and resource mapping directory is specified.
web.xml
<servlet>
  <servlet-name>appServlet</servlet-name>
  <servlet-class>
    org.springframework.web.servlet.DispatcherServlet
  </servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>
      classpath:ctx-core/servlet-context.xml
    </param-value> 
  </init-param>
  <load-on-startup>2</load-on-startup>
</servlet>
servlet-context.xml
<mvc:resources mapping=”/resources/**” location=”/resources/” />
<mvc:resources mapping="/demo/**" location="/demo/" />
Here is the controller to serve our react application
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class MainController {
  @RequestMapping(method={RequestMethod.GET})
  public String index() {
    return “index”;
  }
}
The index.jsp will simply include the build of our react app
<%@include file=”/demo/build/index.html”%>
Now if you run your spring application and browse localhost:8080/SpringWebReact you should see your react app up and running!