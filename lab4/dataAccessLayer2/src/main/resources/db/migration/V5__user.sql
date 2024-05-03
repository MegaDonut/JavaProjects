ALTER TABLE user_auth
    ADD CONSTRAINT uc_user_auth_master UNIQUE (master_id);

ALTER TABLE user_auth
    ALTER COLUMN master_id DROP NOT NULL;