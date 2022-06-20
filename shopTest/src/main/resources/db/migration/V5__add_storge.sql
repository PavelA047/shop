CREATE TABLE file_info_metadata
(
    id        serial,
    hash_id   int8,
    filename  text not null,
    load_date timestamp default current_timestamp,
    sub_type  int,
    PRIMARY KEY (id)
);

CREATE TABLE hash_table
(
    id   serial,
    hash uuid,
    primary key (id)
);

ALTER TABLE file_info_metadata
    ADD CONSTRAINT hash
        FOREIGN KEY (hash_id)
            REFERENCES hash_table (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;