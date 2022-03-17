# Performance
## test environment
### hardware
| Model	 | CPU	| Memory|
| -------|------| ------|
|MacBook Pro (15-inch, 2019)| 2.6 GHz Intel Core i7 | 16 GB 2400 MHz DDR4|

### software
| software name | software version|
| --------------| ----------------|
| CPython	| Python 3.9.7    |
| java8	        | java version "1.8.0_211" Java(TM) SE Runtime Environment (build 1.8.0_211-b12) Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode) |
| maven	        | Apache Maven 3.6.1|
| PEMJA	        | 0.1.2 |
| Jython        | 2.7.2 |
| JEP           | 3.9.1 |

## test data
| type of input arguments| type of output arguments| 
| ---------------------- | ----------------------- |
| String | String |

## test code
```python
def upper(s: str):
    return s.upper()
```

## test results
### single thread
|type	| 100 bytes QPS | 100 bytes memory | 1k bytes QPS | 1k bytes memory
|-------| ----------------------| --------------------- | --------------------- | --------------------- |
|jython	                        | 2862	| 1.5G | 2452 | 1.5G |
|jep (SubInterpreter)	        | 60w	| 100M | 30w  | 300M |
|jep (SharedInterpreter)        | 60w   | 100M | 30w  | 300M |
|pemja (sub-interpreter)	| 105w	| 90M  | 41w  | 90M  |
|pemja (multi-thread)	        | 105w	| 90M  | 39w  | 90M  |
|java	| 320w	| 270M | 33w  | 270M |


### three threads
|type	| 100 bytes QPS(single thread) | 100 bytes memory | 1k bytes QPS (single thread) | 1k bytes memory
|-------| ----------------------| --------------------- | --------------------- | --------------------- |
|jython	                        | 1412	| 1.5G | 1475  | 1.5G |
|jep (SubInterpreter)	        | 7w	| 100M | 6.6w  | 300M |
|jep (SharedInterpreter)        | 7w    | 100M | 6.6w  | 300M |
|pemja (sub-interpreter)	| 9w	| 90M  | 9w    | 90M  |
|pemja (multi-thread)	        | 9w	| 90M  | 9w    | 90M  |
|java	| 320w	| 850M | 33w  | 850M |