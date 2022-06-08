# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* Service runs on 8080 default using an in memory redis cache for persistence
* It basically has got 2 GET end points
	1. To retrieve the event by event ID sample --- http://localhost:8080/events/{eventId} 
	2. Push notifications for the event which pushes the event every 5 seconds. It is under different controller. Please 		read more on the code comments. Sample URL - http://localhost:8080/notifications/football/{eventId}
	
* It has got one POST end point to save and update the event, both handled on the same end point
    Sample URL -   http://localhost:8080/events/footBallEvent
    Sample payload - {
    "event": "teamA-teamB",
    "score": "98-7"
}


* There is tests written to show case the way I would go about writing unit test, integrations tests. Ideally I would like to test files for each n every Java class if possible.
***NOTE : My idea to show case the way I would write the tests.


* 


