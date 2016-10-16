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




ALTER TABLE "question" ADD CONSTRAINT "question_fk0" FOREIGN KEY ("answer_id") REFERENCES "answer"("id");


ALTER TABLE "test_2_question" ADD CONSTRAINT "test_2_question_fk0" FOREIGN KEY ("test_id") REFERENCES "test"("id");
ALTER TABLE "test_2_question" ADD CONSTRAINT "test_2_question_fk1" FOREIGN KEY ("question_id") REFERENCES "question"("id");


ALTER TABLE "test_2_subject" ADD CONSTRAINT "test_2_subject_fk0" FOREIGN KEY ("test") REFERENCES "test"("id");
ALTER TABLE "test_2_subject" ADD CONSTRAINT "test_2_subject_fk1" FOREIGN KEY ("subject") REFERENCES "subject"("id");
