# 2.2 Spring Boot Secure API

**Goals**

* Implement a secure API using the JSON Web Token open standard (RFC 7519). 
* Consume the API from a React JS application.

### Part 1: Implement the API

1. Clone this repository. Compile and run the Spring Boot project.
 
2. Verify that authentication works by issuing the following command from the console:

**This only works in Java 8**
If Java 11 is used, I get this error

![Java11Error](https://github.com/jualme/IETI-LAB8/blob/master/Images/Login%20post1.PNG)    

With Java 8

![Java8Fix](https://github.com/jualme/IETI-LAB8/blob/master/Images/Login%20Fix.PNG)  

3. Open the following url and try to understand the message you get from the server:

http://localhost:8080/api/test

![Test1](https://github.com/jualme/IETI-LAB8/blob/master/Images/test1.PNG)  

Then try to locate the code fragment where the exception is thrown.

4. Comment the line 13 *@Bean* on the *SpringBootSecureApiApplication* class. Re-run the project and go to the endpoint again:

http://localhost:8080/api/test

![Test2](https://github.com/jualme/IETI-LAB8/blob/master/Images/test2.PNG)  
                    
Why do you think it works now? Discuss your findings with your classmates.           

* Works because the security for the path api/* was disabled         

5. Implement the logic to verify the user's credentials on the *UserController* and the *UserServiceImpl* classes.

![Security1](https://github.com/jualme/IETI-LAB8/blob/master/Images/Logincheck1.PNG)  

![Security2](https://github.com/jualme/IETI-LAB8/blob/master/Images/Logincheck2.PNG)  

6. Add the *TaskController* and services implemented on the previous lab. Ensure the endpoints are exposed under the */api* path. 

7. Verify that your implementation secures all the exposed methods under the */api* path in your API.

![endpoint](https://github.com/jualme/IETI-LAB8/blob/master/Images/TestEndpoint.PNG)  
    
### Part 2: Consume the API from ReactJS project

1. Open the *Task Planner App* React JS project.

2. Install the axios node package

    ````
     npm install axios --save
    ````

##### Implement an API call in the Login View
    
3. Make a first request to the API to authenticate on the method *componentDidMount()*:

    ```` Javascript
           axios.post('http://localhost:8080/user/login', {
                 username: 'xyz',
                 password: 'password'
             })
                 .then(function (response) {
                     console.log(response.data);
                 })
                 .catch(function (error) {
                     console.log(error);
                 });
    ````
    
4. Save the returned authentication token into the local storage.

5. Implement the logic to validate if the user has a valid token. Then redirect to the Main View.

##### Make other calls to your API

6. Create an instance of the Axios client that contains the token inside the Authorization header

    ```` Javascript
        this.axios = axios.create({
                baseURL: 'http://localhost:8080/api/',
                timeout: 1000,
                headers: {'Authorization': 'Bearer ' + token}
            });
    ````
    
    
7. Make a request to the API with the Axios client instance for retrieving the Tasks List.

8. Create an additional call to Post new Tasks to the API.