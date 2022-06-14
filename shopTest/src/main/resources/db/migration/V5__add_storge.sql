CREATE TABLE file_info_metadata
(
    hash      uuid,
    filename text not null,
    load_date timestamp default current_timestamp,
    sub_type  int,
    PRIMARY KEY (hash)
)