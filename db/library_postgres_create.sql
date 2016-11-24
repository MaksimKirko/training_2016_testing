CREATE TABLE "quiz" (
	"id" serial NOT NULL,
	"subject_id" bigint NOT NULL,
	"title" character varying(128) NOT NULL UNIQUE,
	"description" character varying(512),
	CONSTRAINT quiz_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "question" (
	"id" serial NOT NULL,
	"text" character varying(512) NOT NULL UNIQUE,
	"hint" character varying(512),
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



CREATE TABLE "quiz_2_question" (
	"quiz_id" bigint NOT NULL,
	"question_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "subject" (
	"id" serial NOT NULL,
	"title" character varying(128) NOT NULL UNIQUE,
	"description" character varying(512),
	CONSTRAINT subject_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "student" (
	"id" serial NOT NULL,
	"first_name" character varying(256) NOT NULL,
	"last_name" character varying(256) NOT NULL,
	"age" int,
	"course" character varying(256),
	"email" character varying(256) NOT NULL UNIQUE,
	"password" character varying(128) NOT NULL,
	"role_id" bigint NOT NULL,
	CONSTRAINT student_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "grade" (
	"id" serial NOT NULL,
	"student_id" bigint NOT NULL,
	"quiz_id" bigint NOT NULL,
	"mark" float4 NOT NULL,
	CONSTRAINT grade_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "question_2_answer" (
	"question_id" bigint NOT NULL,
	"answer_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "role" (
	"id" serial NOT NULL,
	"type" character varying(256) NOT NULL,
	CONSTRAINT role_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "quiz" ADD CONSTRAINT "quiz_fk0" FOREIGN KEY ("subject_id") REFERENCES "subject"("id");



ALTER TABLE "quiz_2_question" ADD CONSTRAINT "quiz_2_question_fk0" FOREIGN KEY ("quiz_id") REFERENCES "quiz"("id");
ALTER TABLE "quiz_2_question" ADD CONSTRAINT "quiz_2_question_fk1" FOREIGN KEY ("question_id") REFERENCES "question"("id");


ALTER TABLE "student" ADD CONSTRAINT "student_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("id");

ALTER TABLE "grade" ADD CONSTRAINT "grade_fk0" FOREIGN KEY ("student_id") REFERENCES "student"("id");
ALTER TABLE "grade" ADD CONSTRAINT "grade_fk1" FOREIGN KEY ("quiz_id") REFERENCES "quiz"("id");

ALTER TABLE "question_2_answer" ADD CONSTRAINT "question_2_answer_fk0" FOREIGN KEY ("question_id") REFERENCES "question"("id");
ALTER TABLE "question_2_answer" ADD CONSTRAINT "question_2_answer_fk1" FOREIGN KEY ("answer_id") REFERENCES "answer"("id");

