# DictionaryServer
This project is to implement a multi-threaded Dictionary Server using a client-server architecture. The system allows multiple clients to connect concurrently. Clients can do four types of operations which are querying a word from dictionary, adding a word and its meanings to the dictionary, removing a word from the dictionary and update an existing word in the dictionary.

## How to run
Run server

```sh
$ java -jar DictionaryServer.jar <port> <dictionary-file>
```

Run client

```sh
$ java -jar DictionaryClient.jar <server-ip> <port>
```

## Interface
The interface is shown below. Should be very straightforward to use.  :)

<img width="418" alt="image" src="https://user-images.githubusercontent.com/88305416/208574557-8e7c127d-ec26-40cc-a60d-b681210a3011.png">
