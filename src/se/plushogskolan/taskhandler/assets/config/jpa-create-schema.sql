CREATE TABLE WorkItem (
  id INTEGER PRIMARY KEY,
  createdBy varchar(255),
  createdDate date ,
  description varchar(255),
  lastModifiedDate date,
  name varchar(255),
  status INTEGER
);
