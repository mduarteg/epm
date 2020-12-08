-- Table: public."EVENT_RATE"

-- DROP TABLE public."EVENT_RATE";

CREATE TABLE public."EVENT_RATE"
(
    id uuid NOT NULL,
    event_type character varying(50) COLLATE pg_catalog."default" NOT NULL,
    effective_date timestamp with time zone NOT NULL,
    uom character varying(100) COLLATE pg_catalog."default" NOT NULL,
    unit_amount integer NOT NULL,
    unit_rate integer NOT NULL,
    CONSTRAINT "EVENT_RATE_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."EVENT_RATE"
    OWNER to postgres;