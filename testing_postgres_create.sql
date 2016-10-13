CREATE TABLE "test" (
	"id" serial NOT NULL,
	"title" character varying(128) NOT NULL UNIQUE,
	"subject" character varying(128) UNIQUE,
	CONSTRAINT test_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "question" (
	"id" serial NOT NULL,
	"text" TEXT(512) NOT NULL UNIQUE,
	"answer_id" bigint NOT NULL,
	CONSTRAINT question_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "answer" (
	"id" serial NOT NULL,
	"text" TEXT(512) NOT NULL UNIQUE,
	CONSTRAINT answer_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "test_to_question" (
	"test_id" bigint NOT NULL,
	"question_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);




ALTER TABLE "question" ADD CONSTRAINT "question_fk0" FOREIGN KEY ("answer_id") REFERENCES "answer"("id");


ALTER TABLE "test_to_question" ADD CONSTRAINT "test_to_question_fk0" FOREIGN KEY ("test_id") REFERENCES "test"("id");
ALTER TABLE "test_to_question" ADD CONSTRAINT "test_to_question_fk1" FOREIGN KEY ("question_id") REFERENCES "question"("id");

