-- Table: public."RATED_EVENT"

-- DROP TABLE public."RATED_EVENT";

CREATE TABLE public."RATED_EVENT"
(
    id uuid NOT NULL,
    event_type character varying(50) COLLATE pg_catalog."default" NOT NULL,
    target_resource character varying(200) COLLATE pg_catalog."default" NOT NULL,
    event_start_time timestamp with time zone NOT NULL,
    event_unit_consumed integer NOT NULL,
    total_charge double precision NOT NULL,
    CONSTRAINT "RATED_EVENT_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."RATED_EVENT"
    OWNER to postgres;