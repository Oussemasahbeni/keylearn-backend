CREATE TABLE notifications
(
    id               UUID         NOT NULL,
    created_by       VARCHAR(255),
    last_modified_by VARCHAR(255),
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    version          SMALLINT     NOT NULL,
    type             VARCHAR(255) NOT NULL,
    data             JSONB,
    user_id          UUID         NOT NULL,
    is_read          BOOLEAN      NOT NULL,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                      UUID         NOT NULL,
    created_by              VARCHAR(255),
    last_modified_by        VARCHAR(255),
    created_at              TIMESTAMP WITHOUT TIME ZONE,
    updated_at              TIMESTAMP WITHOUT TIME ZONE,
    version                 SMALLINT     NOT NULL,
    first_name              VARCHAR(255) NOT NULL,
    last_name               VARCHAR(255) NOT NULL,
    email                   VARCHAR(255) NOT NULL UNIQUE,
    profile_picture         VARCHAR(255),
    gender                  VARCHAR(255),
    notification_preference VARCHAR(255),
    country                 VARCHAR(255),
    date_of_birth           date,
    address                 VARCHAR(255),
    phone_number            VARCHAR,
    country_code            VARCHAR,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE INDEX idx_is_read ON notifications (is_read);

CREATE INDEX idx_user_id_on_notifications ON notifications (user_id);