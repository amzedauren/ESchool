-- Table: public.e_school_user

-- DROP TABLE public.e_school_user;

CREATE TABLE public.e_school_user
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ESchoolUser_pkey" PRIMARY KEY (id),
    CONSTRAINT e_school_user_email_key UNIQUE (email)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.e_school_user
    OWNER to rdsuser;


-- Table: public.e_school_student

-- DROP TABLE public.e_school_student;

CREATE TABLE public.e_school_student
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    phone_number character varying(15) COLLATE pg_catalog."default",
    added_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT e_school_student_pkey PRIMARY KEY (id),
    CONSTRAINT added_by FOREIGN KEY (added_by)
        REFERENCES public.e_school_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.e_school_student
    OWNER to rdsuser;


-- Table: public.e_school_group

-- DROP TABLE public.e_school_group;

CREATE TABLE public.e_school_group
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    group_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    teacher_id character varying COLLATE pg_catalog."default",
    CONSTRAINT e_school_group_pkey PRIMARY KEY (id),
    CONSTRAINT e_school_group_teacher_id_fkey FOREIGN KEY (teacher_id)
        REFERENCES public.e_school_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.e_school_group
    OWNER to rdsuser;





-- Table: public.e_school_groups_students

-- DROP TABLE public.e_school_groups_students;

CREATE TABLE public.e_school_groups_students
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    group_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    student_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT e_school_groups_students_pkey PRIMARY KEY (id),
    CONSTRAINT e_school_groups_students_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES public.e_school_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT e_school_groups_students_student_id_fkey FOREIGN KEY (student_id)
        REFERENCES public.e_school_student (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.e_school_groups_students
    OWNER to rdsuser;