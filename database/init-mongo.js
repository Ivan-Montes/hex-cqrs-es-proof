
let conn = new Mongo();
db = conn.getDB("mongo-read-db");

db.clients.createIndex({ clientId: 1 }, {unique: true});
db.clients.insertOne({ clientId:UUID("3fa85f64-5717-4562-b3fc-2c963f66afa1"), name:"Peter Pork", _class: 'dev.ime.infrastructure.entity.ClientMongoEntity'});
db.clients.insertOne({ clientId:UUID("3fa85f64-5717-4562-b3fc-2c963f66afa2"), name:"Gwen Stacy", _class: 'dev.ime.infrastructure.entity.ClientMongoEntity'});
db.clients.insertOne({ clientId:UUID(), name:"Mary Jane Watson", _class: 'dev.ime.infrastructure.entity.ClientMongoEntity'});
db.clients.insertOne({ clientId:UUID(), name:"Miles Morales", _class: 'dev.ime.infrastructure.entity.ClientMongoEntity'});
db.clients.insertOne({ clientId:UUID(), name:"Spiderman Noir", _class: 'dev.ime.infrastructure.entity.ClientMongoEntity'});

db.flights.createIndex({ flightId: 1}, {unique: true});
db.flights.insertOne({ flightId: UUID(), origin:"NYC", destiny:"Tombouctou", clientSet:[], _class: 'dev.ime.infrastructure.entity.FlightMongoEntity'})
db.flights.insertOne({ flightId: UUID(), origin:"Paris", destiny:"Tanger", clientSet:[], _class: 'dev.ime.infrastructure.entity.FlightMongoEntity'})
db.flights.insertOne({ flightId: UUID(), origin:"Tokio", destiny:"Beijing", clientSet:[], _class: 'dev.ime.infrastructure.entity.FlightMongoEntity'})
db.flights.insertOne({ flightId: UUID(), origin:"Tierra del Fuego", destiny:"Croatia", clientSet:[], _class: 'dev.ime.infrastructure.entity.FlightMongoEntity'})
db.flights.insertOne({ flightId: UUID(), origin:"Antarctica", destiny:"Cairo", clientSet:[], _class: 'dev.ime.infrastructure.entity.FlightMongoEntity'})
