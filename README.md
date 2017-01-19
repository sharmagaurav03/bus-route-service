# Bus Route Program

### Problem

Travel portal is adding a new bus provider to its system. In order to implement a very
specific requirement of the bus provider, the system needs to be able to filter
direct connections. Travel portal has access to a weekly updated list of bus routes
in form of a **bus route data file**. As this provider has a lot of long bus
routes, micro-service shall come up with a proper service to quickly answer if two given
stations are connected by a bus route.

### Class Diagram
####Class diagram for upload service data file:
![alt tag](https://github.com/sharmagaurav03/bus-route-service/blob/master/bus-route-service-data-file-upload.jpg)

####Class diagram for route service query:
![alt tag](https://github.com/sharmagaurav03/bus-route-service/blob/master/bus-route-service-query.jpg)

### Task

The bus route data file provided by the bus provider contains a list of bus
routes. These routes consist of an unique identifier and a list of stations
(also just unique identifiers). A bus route **connects** its list of stations.

This micro service is able to answer whether there
is a bus route providing a direct connection between two given stations. *Note:
The station identifiers given in a query may not be part of any bus route!*


### Bus Route Data

The first line of the data gives the number **N** of bus routes, followed by
**N** bus routes. For each bus route there will be **one** line containing a
space separated list of integers. This list contains at least three integers. The
**first** integer represents the bus **route id**. The bus route id is unique
among all other bus route ids in the input. The remaining integers in the list
represent a list of **station ids**. A station id may occur in multiple bus
routes, but can never occur twice within the same bus route.

assumption is 100,000 as upper limit for the number of bus routes, 1,000,000 as
upper limit for the number of stations, and 1,000 as upper limit for the number
of station of one bus route. Therefore, internal data structure should
still fit into memory on a suitable machine.

*Note: The bus route data file will be a local file and your service will get
the path to file as the first command line argument. Your service will get
restarted if the file or its location changes.*


### REST API

Micro service has implemented a REST-API supporting Two URLs. One PUT request to upload bus route data and one to query the route.

1) GET serves
`http://localhost:8088/api/direct?dep_sid={}&arr_sid={}`. The parameters
`dep_sid` (departure) and `arr_sid` (arrival) are two station ids (sid)
represented by 32 bit integers.

The response is a single JSON Object:

```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "dep_sid": {
      "type": "integer"
    },
    "arr_sid": {
      "type": "integer"
    },
    "direct_bus_route": {
      "type": "boolean"
    }
  },
  "required": [
    "dep_sid",
    "arr_sid",
    "direct_bus_route"
  ]
}
```

The `direct_bus_route` field has to be set to `true` if there exists a bus route
in the input data that connects the stations represented by `dep_sid` and
`arr_sid`. Otherwise `direct_bus_route` must be set to `false`.

1) PUT serves
`http://localhost:8088/api/route/data/load`. The request body
has local file path for the route data. 
`DRIVE:/XXX/YYY/ZZZ/SomeFolder/FileName`

```
The response is a either status 200 or error.
```

Once file is uploaded successfully, route data is refreshed without bringing down the micro-service.


### Example Data

Bus Routes Data File:
```
3
0 0 1 2 3 4
1 3 1 6 5
2 0 6 4
```

PUT: Upload the route data file

```
http://localhost:8088/api/route/data/load
```
Body:
```
C:/routeData/test-routes
```

Response:
```
Status: 200
```
Query:
````
http://localhost:8088/api/direct?dep_sid=3&arr_sid=6
```

Response:
```
{
    "dep_sid": 3,
    "arr_sid": 6,
    "direct_bus_route": true
}
```

### Packaging

Micro service contains two simple bash scripts in the top level
repository directory.

- `build.sh`: builds the project.
- `service.sh`: starts / stops your micro service. Accepts `start|stop|block`
  and the path to a **bus routes data file** as arguments (`bash service.sh
  start FILE`). After micro service gets started it shall answer queries
  until it is terminated. 

### Shipping

```
project_folder
├── src
├── tests --> Contains sample test script.
├── routes --> Contains test routes data files.
├── class-diagrams ---> Contains class diagrams of both flows.
├── pom.xml
├── build.sh
└── service.sh
```
