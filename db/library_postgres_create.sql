CREATE TABLE "test" (
	"id" serial NOT NULL,
	"title" character varying(128) NOT NULL UNIQUE,
	CONSTRAINT test_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "question" (
	"id" serial NOT NULL,
	"text" character varying(512) NOT NULL UNIQUE,
	"answer_id" bigint NOT NULL,
	CONSTRAINT question_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "answer" (
	"id" serial NOT NULL,
	"text" character varying(512) NOT NULL UNIQUE,
	CONSTRAINT answer_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "test_2_question" (
	"test_id" bigint NOT NULL,
	"question_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "subject" (
	"id" serial NOT NULL,
	"name" character varying(128) NOT NULL UNIQUE,
	"description" character varying(512),
	CONSTRAINT subject_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "test_2_subject" (
	"test" bigint NOT NULL,
	"subject" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user" (
	"id" serial NOT NULL,
	"first_name" character varying(256) NOT NULL,
	"last_name" character varying(256) NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_details" (
	"id" serial NOT NULL,
	"email" character varying(256) NOT NULL UNIQUE,
	"password" character varying(256) NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "grades" (
	"user_id" bigint NOT NULL,
	"test_id" bigint NOT NULL,
	"mark" bigint NOT NULL
) WITH (
  OIDS=FALSE
);




ALTER TABLE "question" ADD CONSTRAINT "question_fk0" FOREIGN KEY ("answer_id") REFERENCES "answer"("id");


ALTER TABLE "test_2_question" ADD CONSTRAINT "test_2_question_fk0" FOREIGN KEY ("test_id") REFERENCES "test"("id");
ALTER TABLE "test_2_question" ADD CONSTRAINT "test_2_question_fk1" FOREIGN KEY ("question_id") REFERENCES "question"("id");


ALTER TABLE "test_2_subject" ADD CONSTRAINT "test_2_subject_fk0" FOREIGN KEY ("test") REFERENCES "test"("id");
ALTER TABLE "test_2_subject" ADD CONSTRAINT "test_2_subject_fk1" FOREIGN KEY ("subject") REFERENCES "subject"("id");


ALTER TABLE "user_details" ADD CONSTRAINT "user_details_fk0" FOREIGN KEY ("id") REFERENCES "user"("id");

ALTER TABLE "grades" ADD CONSTRAINT "grades_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "grades" ADD CONSTRAINT "grades_fk1" FOREIGN KEY ("test_id") REFERENCES "test"("id");
