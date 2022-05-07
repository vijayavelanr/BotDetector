# BotDetector

Implementation

1. Bot Generates Randon Numbers to be summed by client
2. Bot also uses uuid to keep track of client to identify known client and challenge question // may be thinking there is a better way or we could use just the hash for the request.
3. since clients answer come back part of response body, '{"question":"Please sum the numbers 8,2,6","id":"b00f4c8e-13eb-4f0c-9736-76e33370fc8b",**"answer": 12**}', direct hash to request would not work.
4. Maybe session could have been used to keep track of client requests, but scalability should thought throw to replicate session in-memory.

git clone git@github.com:vijayavelanr/BotDetector.git
set JAVA_HOME=C:\Users\developer\.jdks\corretto-1.8.0_332\bin

cd BotDetector/scripts

        Starts the server on default PORT 8080
        ./runBot.cmd

Alternatively to change the port to something we need, maybe we choose 9090
        ./runBot.cmd 9090

**Once server is started, we can use the curl command to query the server
**
$  curl -s http://localhost:8080/bot/identity
{"question":"Please sum the numbers 8,2,6","id":"b00f4c8e-13eb-4f0c-9736-76e33370fc8b"}


**When Sending Connect Answer
================================================================
**
$  **curl -v -s http://localhost:8080/bot/prove -X POST -d '{"question":"Please sum the numbers 8,6,8","id":"ebbd2f0e-bcfd-4235-a59c-120ab1f15884","answer": 22}'  -H "Content-Type: application/json"**


**Great Works* **  Trying 127.0.0.1:8080...

* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /bot/prove HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.81.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 100
>
} [100 bytes data]
* Mark bundle as not supporting multiuse
**< HTTP/1.1 200
**< Content-Type: application/json
< Content-Length: 11
< Date: Fri, 06 May 2022 21:33:40 GMT
<
{ [11 bytes data]
* Connection #0 to host localhost left intact



**While Sending Incorrect Answer
================================================================
** curl -v -s http://localhost:8080/bot/prove -X POST -d '{"question":"Please sum the numbers 8,2,6","id":"b00f4c8e-13eb-4f0c-9736-76e33370fc8b","answer": 12}'  -H "Content-Type: application/json"

*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /bot/prove HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.81.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 100
>
} [100 bytes data]
* Mark bundle as not supporting multiuse
**< HTTP/1.1 400
**< Content-Length: 0
< Date: Fri, 06 May 2022 21:23:10 GMT
< Connection: close
<
* Closing connection 0


**While Sending Tampered uid Data**
=================================================================

$  curl -v -s http://localhost:8080/bot/prove -X POST -d '{"question":"Please sum the numbers 6,1,3","id":"f4efb905-e974-4e3e-a857-94da51776d","answer": 10}'  -H "Content-Type: application/json"
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /bot/prove HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.81.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 98
>
} [98 bytes data]
* Mark bundle as not supporting multiuse
**< HTTP/1.1 400
**< Content-Length: 0
< Date: Fri, 06 May 2022 21:46:20 GMT
< Connection: close
<
* Closing connection 0

**While Sending Tampered Question**
====================================================================
$  curl -v -s http://localhost:8080/bot/prove -X POST -d '{"question":"Please sum the numbers 6,1,1","id":"f4efb905-e974-4e3e-a857-94da5177fc6d","answer": 8}'  -H "Content-Type: application/json"
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /bot/prove HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.81.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 99
>
} [99 bytes data]
* Mark bundle as not supporting multiuse
**< HTTP/1.1 400
**< Content-Length: 0
< Date: Fri, 06 May 2022 21:47:22 GMT
< Connection: close
<
* Closing connection 0

