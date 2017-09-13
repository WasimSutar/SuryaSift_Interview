# SuryaSift_Interview
Repo contains solution for Surya Software Interview Problem statement

Problem statement:
--------------------
A JSON over HTTP API is deployed at http://surya-interview.appspot.com. The goal is to performance test it. This involves making two requests.

The first request is an HTTP GET request to http://surya-interview.appspot.com/message that contains a header titled X-Surya-Email-Id. This must contain your email address. The response will be a HTTP 200 (unless there's an error - if it's a 400 class error, you did something wrong; if it's a 500 class error, please let me know via email) and contain a JSON body which has the email id you sent as a header, as well as a uuid. Example:

200 OK
{
  "emailId": "gps@surya-soft.com",
  "uuid": "fa674442-c513-4b1f-8dce-47f70307143c"
}
The second request is an HTTP POST request to http://surya-interview.appspot.com/message. The post body must be JSON and must have two keys: emailId (exactly the same value as you sent before), and uuid (the value that was returned in the previous response). Example:

{
  "emailId": "gps@surya-soft.com",
  "uuid": "fa674442-c513-4b1f-8dce-47f70307143c"
}
The response if successful will be a HTTP 200, with a body that reads Success. If you get a 400 class error you did something wrong. If you get a 500 class error, please let me know via email.

You are expected to make each of these requests 100 times and provide the following statistics for the response time of each API:

10th percentile
50th percentile
90th percentile
95th percentile
99th percentile
Mean
Standard Deviation
Please note that your solution must be concurrent. That is, you cannot make 200 requests in sequence. You should be making at least 10 requests at a time. Obviously, since one of the requests requires data from the previous response, you will have to do those in sequence.


Solution :
Language used: Java 
Jar Files used : java-json.jar for JSON operations
IDE used : Eclipse Juno

To run the program
>javac APITest.java

>java APITest



Thanks





