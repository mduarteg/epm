-- Table: public."REJECTED_EVENT"

-- DROP TABLE public."REJECTED_EVENT";

CREATE TABLE public."REJECTED_EVENT"
(
    id uuid NOT NULL,
    target_resource character varying(200) COLLATE pg_catalog."default",
    event_start_time timestamp with time zone,
    event_unit_consumed integer,
    rejection_reason text COLLATE pg_catalog."default" NOT NULL,
    event_type character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "REJECTED_EVENT_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."REJECTED_EVENT"
    OWNER to postgres;