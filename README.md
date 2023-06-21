# Reward_Points_Calculator

Download or clone this project, import this to your favourite IDE and run. This project will run on default spring boot port on localhost is 8080. I have implemented 4 REST end points for serving the purpose of getting all the transaction done by customers, calculating their rewardpoints per month and total reward points gained by them. The end points are :

HTTP Operations for accessing endpoints are:

1. GET	http://localhost:8080/transactions/ - To fetch all the transactions done by all the customers
2. GET	http://localhost:8080/transactions/{customerId} -  To fetch the transcations done by a particular customer using their customer Id (put any one of these IDs 1001,1002 or 1003).
3. GET  http://localhost:8080/transactions/{customerId}/reward-points/total - To fetch the total reward points gained by a customer (put 
   any one of these IDs 1001,1002 or 1003 inplace of {customerId).
4. GET  http://localhost:8080/transactions/{customerId}/reward-points - To fetch the reward points of a customer per month.


# Accessing the H2 database 
After starting the project on port 8080, first need to login the H2 Database using the http://localhost:8080/h2-console/ url. just insert username and the password that is set in application.properties.

1. JDBD URL: jdbc:h2:mem:my_db
2. UserName: sa
3. Password: password
