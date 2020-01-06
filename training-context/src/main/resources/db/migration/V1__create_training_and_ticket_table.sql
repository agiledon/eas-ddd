CREATE TABLE training (
    id VARCHAR(50) NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    beginTime TIMESTAMP NOT NULL,
    endTime TIMESTAMP NOT NULL,
    place VARCHAR(200) NOT NULL,
    courseId VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE ticket (
    id VARCHAR(50) NOT NULL,
    ticketStatus VARCHAR(20) NOT NULL,
    trainingId VARCHAR(50) NOT NULL,
    nomineeId VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE ticket_history (
    id VARCHAR(50) NOT NULL,
    ticketId VARCHAR(50) NOT NULL,
    ownerId VARCHAR(50) NOT NULL,
    ownerType VARCHAR(20) NOT NULL,
    fromStatus VARCHAR(20) NOT NULL,
    toStatus VARCHAR(20) NOT NULL,
    OperationType VARCHAR(20) NOT NULL,
    operatorId VARCHAR(50) NOT NULL,
    operatorName VARCHAR(20) NOT NULL,
    operatedAt TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE learning (
    id VARCHAR(50) NOT NULL,
    courseId VARCHAR(50) NOT NULL,
    trainingId VARCHAR(50) NOT NULL,
    traineeId VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE candidate (
    id VARCHAR(50) NOT NULL,
    employeeId VARCHAR(50) NOT NULL,
    trainingId VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE valid_date (
    id VARCHAR(50) NOT NULL,
    trainingId VARCHAR(50) NOT NULL,
    deadline TIMESTAMP NOT NULL,
    validDateType VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE mail_template (
    id VARCHAR(50) NOT NULL,
    template VARCHAR(1000) NOT NULL,
    templateType VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
);