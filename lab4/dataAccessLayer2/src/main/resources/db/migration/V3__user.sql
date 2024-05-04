CREATE TABLE user_auth
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login     VARCHAR(255),
    password  VARCHAR(255),
    master_id INTEGER                                  NOT NULL,
    CONSTRAINT pk_user_auth PRIMARY KEY (id)
);

ALTER TABLE user_auth
    ADD CONSTRAINT FK_USER_AUTH_ON_MASTER FOREIGN KEY (master_id) REFERENCES master (id);