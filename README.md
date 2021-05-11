# Payment Processing
Home task for application process for SOFTWARE ENGINEERING INTERN position in Luminor.  
A compact web service for payments processing build in Java using Spring Boot.

## :clipboard: Functionality
There are 4 main endpoints: ***POST*** and ***GET*** for both ***/payments*** and ***/payment-files***

### GET /Payments
Provides a form for adding new payment entries and also shows a table with the list of all entries in the database.
It contains *id*, *amount*, *debtorIban* and *createdAt* fields.

Endpoint also accepts optional ***debtorIban*** filter in the query param.  
To try this out, add value, for example *EE356437904816712537*, to ***http://localhost:8080/payments/*** ,  
So it will look like: http://localhost:8080/payments/EE356437904816712537


### POST /Payments
Adds a new payment entry. It accepts 2 mandatory fields:

| Field name | Validation rules                                                          |
|------------|---------------------------------------------------------------------------|
| amount     | Greater than 0                                                            |
| debtorIban | Should be a Baltic country (LT, LV, EE) account number. IBAN Regex check. |

All payment entries automatically assigned with TimeStamp and UUID

### GET /Payment-form
Provides a form for adding new payment entries in a bulk by choosing a *.csv* file

### POST /Payment-form
Accepts CSV and adds payments to database.  
CSV file example format:

| amount | debrorIban           |
|--------|----------------------|
| 5.0    | LT356437978869712537 |
| 125.0  | EE839257977125372452 |

All payments are also validated according to the rules in ***POST /Payments***  

There is a file ***test-file.csv*** in a root directory used for testing. It contains correct and incorrect values (2/7 are correct).  
P.S. Statistics on added entries shown in terminal

### Additional: Country by IP
Once any of ***/Post*** called by the client, server tries to get the caller IP from ***X-Forwarded-For*** request header.  
Using https://ip-api.com/ API, it gets the status of IP and if it is ***"success"***, then it returns country corresponding to client IP address.  
With ***POST /Payments*** information is printed on the page after call, and with ***POST /Payment-form*** it is printed in terminal.

## :gear: Run instructions

**-- Compiled with JDK 1.8 --**

**1. Open command-line in the folder containing .jar and run the command:**

```
$ java -jar payment-processor-0.0.1-SNAPSHOT.jar
```
Don't close the terminal or terminate the program

**2. Open browser and go to http://localhost:8080/payments or http://localhost:8080/payment-files**