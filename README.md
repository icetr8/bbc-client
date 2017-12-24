# bcc-client

Bit Block Chat. The Messenger api and coins.ph api endpoint. A bot deemed to analyze trading in
cryptocurrency with control/notification in fb messenger.

- [Facebook Page](https://www.facebook.com/Bit-Block-Chat-326577454490506/)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for
development and testing purposes. See deployment for notes on how to deploy the project on a live
system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo
### To Do's


### Notes
- Incoming webhook event sample post

```
{
	"object": "page",
	"entry": [
		{
			"id": "326577454490506",
			"time": 1514118392435,
			"messaging": [
				{
					"sender": {
						"id": "1817289878344392"
					},
					"recipient": {
						"id": "326577454490506"
					},
					"timestamp": 1514118392079,
					"message": {
						"mid": "mid.$cAAFz9iAGZKtmucJxD1giH0oJCgJw",
						"seq": 870037,
						"text": "The message"
					}
				}
			]
		}
	]
}
```
- [What's the difference between response.body() and object returned by the
  callback?](https://stackoverflow.com/questions/35256701/whats-the-difference-between-response-body-and-object-returned-by-the-callbac)

```
An actual difference however is that response.body() accepts only String, while in the callback you
can return any object that can be serialized to String and most importantly streams.

response.body() should be mostly used in exception handlers and after filters, and the callback
return in normal routes.
```

- [Java JSON deserializer that decodes a string into dictionary of lists or lists of dictionaries of primitive types](https://stackoverflow.com/questions/4403249/is-there-a-java-json-deserializer-that-decodes-a-string-into-dictionary-of-lists)

```
ObjectMapper mapper = new ObjectMapper();
Map<String,Object> map = mapper.readValue(json, Map.class);
```
