INSERT INTO training (id, title, description, beginTime, endTime, place, courseId)
VALUES ("3070c3c9-f809-4a06-8fc4-ff7d330c86ca", "DDD", "DDD Description", "2019-10-12 09:00:00", "2019-10-13 17:00:00", "Beijing Room", "cc70c3c9-f809-4a06-8fc4-ff7d330c86ca");

INSERT INTO ticket (id, ticketStatus, trainingId, nomineeId)
VALUES ("tt70c3c9-f809-4a06-8fc4-ff7d330c86ca", "Available", "3070c3c9-f809-4a06-8fc4-ff7d330c86ca", NULL);

INSERT INTO mail_template(id, template, templateType)
values ("mailc3c9-f809-4a06-8fc4-ff7d330c86ca", "Hi $nomineeName$:\n$url$\n\nHere is the basic information of training:\nTitle: $title$\nDescription: $description$\nBegin time: $beginTime$\nEnd time: $endTime$\nPlace: $place$\n\nThanks! We're excited to have you in the training.\nEAS Team", "Nomination");