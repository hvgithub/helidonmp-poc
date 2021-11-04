Helidon MP POC APIs
From Helidon MP Documentation tried the following:

1. /greet `harvasud@harvasud-mac hvhelidonmpeval % curl http:/localhost:8080/greet
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100    26  100    26    0     0   1368      0 --:--:-- --:--:-- --:--:--  1368
   {
   "message": "Hello World!"
   }`
2. /greet/{name} `harvasud@harvasud-mac hvhelidonmpeval % curl http:/localhost:8080/greet/Harini
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100    27  100    27    0     0   1928      0 --:--:-- --:--:-- --:--:--  1928
   {
   "message": "Hello Harini!"
   }`
3. metrics `harvasud@harvasud-mac hvhelidonmpeval % curl -H "Accept: application/json" http:/localhost:8080/metrics
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100  1094  100  1094    0     0   267k      0 --:--:-- --:--:-- --:--:--  267k
   {
   "application": {
   "io.helidon.restapi.examples.GreetResource.getDefaultMessage": {
   "count": 1,
   "fifteenMinRate": 0.000798361518123311,
   "fiveMinRate": 0.0012365406861280634,
   "max": 3373613,
   "mean": 3373613.0,
   "meanRate": 0.002770124217789369,
   "min": 3373613,
   "oneMinRate": 0.00011711097593598808,
   "p50": 3373613.0,
   "p75": 3373613.0,
   "p95": 3373613.0,
   "p98": 3373613.0,
   "p99": 3373613.0,
   "p999": 3373613.0,
   "stddev": 0.0
   }
   },
   "base": {
   "classloader.loadedClasses.count": 7898,
   "classloader.loadedClasses.total": 7901,
   "classloader.unloadedClasses.total": 3,
   "cpu.availableProcessors": 8,
   "cpu.systemLoadAverage": 2.494140625,
   "gc.time;name=G1 Old Generation": 0,
   "gc.time;name=G1 Young Generation": 22,
   "gc.total;name=G1 Old Generation": 0,
   "gc.total;name=G1 Young Generation": 6,
   "jvm.uptime": 361980,
   "memory.committedHeap": 62914560,
   "memory.maxHeap": 4294967296,
   "memory.usedHeap": 21272672,
   "thread.count": 63,
   "thread.daemon.count": 53,
   "thread.max.count": 66
   },
   "vendor": {
   "requests.count": 16,
   "requests.meter": {
   "count": 16,
   "fifteenMinRate": 0.013584648255066835,
   "fiveMinRate": 0.02787128172063923,
   "meanRate": 0.04435504208341392,
   "oneMinRate": 0.032750307805200454
   }
   }
   }`
4. metrics/application `bash-4.2$ curl -X GET http://localhost:8080/greet
   {"message":"Hello World!"}bash-4.2$
   bash-4.2$
   bash-4.2$ curl -X GET http://localhost:8080/greet
   {"message":"Hello World!"}bash-4.2$
   bash-4.2$
   bash-4.2$ curl -H "Accept: application/json" http://localhost:8080/metrics/application
   {"io.helidon.examples.GreetResource.getDefaultMessage":{"count":2,"meanRate":0.03997626969454188,"oneMinRate":0.02291627214933819,"fiveMinRate":0.006185028160917795,"fifteenMinRate":0.0021673581473008767,"min":142921,"max":3078507,"mean":1610714.0,"stddev":1467793.0,"p50":3078507.0,"p75":3078507.0,"p95":3078507.0,"p98":3078507.0,"p99":3078507.0,"p999":3078507.0}}bash-4.2$
   bash-4.2$
   bash-4.2$ curl -H "Accept: application/json" http://localhost:8080/metrics/application | python -m json.tool
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100   366  100   366    0     0  29357      0 --:--:-- --:--:-- --:--:-- 30500
   {
   "io.helidon.examples.GreetResource.getDefaultMessage": {
   "count": 2,
   "fifteenMinRate": 0.0021434096835125558,
   "fiveMinRate": 0.005982258819174303,
   "max": 3078507,
   "mean": 1610714.0,
   "meanRate": 0.03328545764800754,
   "min": 142921,
   "oneMinRate": 0.019398205577034533,
   "p50": 3078507.0,
   "p75": 3078507.0,
   "p95": 3078507.0,
   "p98": 3078507.0,
   "p99": 3078507.0,
   "p999": 3078507.0,
   "stddev": 1467793.0
   }
   }
   bash-4.2$ curl -X GET http://localhost:8080/greet
   {"message":"Hello World!"}bash-4.2$
   bash-4.2$
   bash-4.2$ curl -H "Accept: application/json" http://localhost:8080/metrics/application | python -m json.tool
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100   381  100   381    0     0  38707      0 --:--:-- --:--:-- --:--:-- 42333
   {
   "io.helidon.examples.GreetResource.getDefaultMessage": {
   "count": 3,
   "fifteenMinRate": 0.003227756239946759,
   "fiveMinRate": 0.009091846282832359,
   "max": 3078507,
   "mean": 1110358.6666666667,
   "meanRate": 0.042563514296529006,
   "min": 109648,
   "oneMinRate": 0.03241134359076627,
   "p50": 142921.0,
   "p75": 3078507.0,
   "p95": 3078507.0,
   "p98": 3078507.0,
   "p99": 3078507.0,
   "p999": 3078507.0,
   "stddev": 1391757.323107333
   }
   }`
5. /health `harvasud@harvasud-mac hvhelidonmpeval % curl http:/localhost:8080/health
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100   529  100   529    0     0   103k      0 --:--:-- --:--:-- --:--:--  103k
   {
   "checks": [
   {
   "name": "deadlock",
   "state": "UP",
   "status": "UP"
   },
   {
   "data": {
   "free": "17.48 GB",
   "freeBytes": 18766823424,
   "percentFree": "7.49%",
   "total": "233.47 GB",
   "totalBytes": 250685575168
   },
   "name": "diskSpace",
   "state": "UP",
   "status": "UP"
   },
   {
   "data": {
   "greeting": "Hello"
   },
   "name": "greeting",
   "state": "UP",
   "status": "UP"
   },
   {
   "data": {
   "free": "38.51 MB",
   "freeBytes": 40378272,
   "max": "4.00 GB",
   "maxBytes": 4294967296,
   "percentFree": "99.48%",
   "total": "60.00 MB",
   "totalBytes": 62914560
   },
   "name": "heapMemory",
   "state": "UP",
   "status": "UP"
   }
   ],
   "outcome": "UP",
   "status": "UP"
   }
   `
5. /health/live `harvasud@harvasud-mac hvhelidonmpeval % curl http:/localhost:8080/health/live
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100   529  100   529    0     0   103k      0 --:--:-- --:--:-- --:--:--  103k
   {
   "checks": [
   {
   "name": "deadlock",
   "state": "UP",
   "status": "UP"
   },
   {
   "data": {
   "free": "17.48 GB",
   "freeBytes": 18768302080,
   "percentFree": "7.49%",
   "total": "233.47 GB",
   "totalBytes": 250685575168
   },
   "name": "diskSpace",
   "state": "UP",
   "status": "UP"
   },
   {
   "data": {
   "greeting": "Hello"
   },
   "name": "greeting",
   "state": "UP",
   "status": "UP"
   },
   {
   "data": {
   "free": "39.66 MB",
   "freeBytes": 41584528,
   "max": "4.00 GB",
   "maxBytes": 4294967296,
   "percentFree": "99.50%",
   "total": "60.00 MB",
   "totalBytes": 62914560
   },
   "name": "heapMemory",
   "state": "UP",
   "status": "UP"
   }
   ],
   "outcome": "UP",
   "status": "UP"
   }`
6. /health/ready `harvasud@harvasud-mac hvhelidonmpeval % curl http:/localhost:8080/health/ready
   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
   Dload  Upload   Total   Spent    Left  Speed
   100    42  100    42    0     0   8400      0 --:--:-- --:--:-- --:--:--  8400
   {
   "checks": [],
   "outcome": "UP",
   "status": "UP"
   }
   harvasud@harvasud-mac hvhelidonmpeval %
   `
7. /tables
    1. `+------------+---------------------+
       | FruitName  | DateEntered         |
       +------------+---------------------+
       | Apple      | 2015-02-15 10:30:00 |
       | Orange     | 2015-02-15 10:30:00 |
       | Banana     | 2015-02-15 10:30:00 |
       | Watermelon | 2015-02-15 10:30:00 |
       | Grapes     | 2015-02-15 10:30:00 |
       | Strawberry | 2015-02-15 10:30:00 |
       +------------+---------------------+`
    2. `harvasud@harvasud-mac hvhelidonmpeval % curl http:/localhost:8080/tables                            
       Fruit: Apple, DateEntered: 2015-02-15 10:30:00
       Fruit: Orange, DateEntered: 2015-02-15 10:30:00
       Fruit: Banana, DateEntered: 2015-02-15 10:30:00
       Fruit: Watermelon, DateEntered: 2015-02-15 10:30:00
       Fruit: Grapes, DateEntered: 2015-02-15 10:30:00
       Fruit: Strawberry, DateEntered: 2015-02-15 10:30:00
       harvasud@harvasud-mac hvhelidonmpeval % `