CREATE TABLE cat
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name      VARCHAR(255),
    birthday  TIMESTAMP WITHOUT TIME ZONE,
    breed     SMALLINT,
    colors    SMALLINT,
    master_id INTEGER                                  NOT NULL,
    CONSTRAINT pk_cat PRIMARY KEY (id)
);

CREATE TABLE cat_cats
(
    cat_1_id  INTEGER NOT NULL,
    cats_2_id INTEGER NOT NULL,
    CONSTRAINT pk_cat_cats PRIMARY KEY (cat_1_id, cats_2_id)
);

CREATE TABLE master
(
    id       INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    birthday TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_master PRIMARY KEY (id)
);

ALTER TABLE cat
    ADD CONSTRAINT FK_CAT_ON_MASTER FOREIGN KEY (master_id) REFERENCES master (id);

ALTER TABLE cat_cats
    ADD CONSTRAINT fk_cat_cats_on_cat_1 FOREIGN KEY (cat_1_id) REFERENCES cat (id);

ALTER TABLE cat_cats
    ADD CONSTRAINT fk_cat_cats_on_cats_2 FOREIGN KEY (cats_2_id) REFERENCES cat (id);